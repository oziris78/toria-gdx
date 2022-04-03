package com.telek.telekgdx.simple.box2d;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.telek.telekgdx.box2d.BodyFactory;
import com.telek.telekgdx.box2d.TBox2D;
import com.telek.telekgdx.box2d.TiledParser;
import com.telek.telekgdx.screens.TScreenUtils;


public class LightAndDoorsTest extends ApplicationAdapter {

    public static final int PPM = 16;

    public static final int SCREEN_WIDTH = 608;
    public static final int SCREEN_HEIGHT = 608;

    public static final int MAX_VELOCITY = 100;
    public static final int PLAYER_WIDTH = 25;
    public static final int PLAYER_HEIGHT = 25;

    private World world;
    private Box2DDebugRenderer b2dr;
    private Body player, box1, box2, box3, box4;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture playerTexture;
    private Sprite playerImage;

    // Garbage variables for performance
    float delta = 0;
    private Vector2 vel = new Vector2();
    private Vector2 pos = new Vector2();

    private Vector2 playerPos = new Vector2();
    private Rectangle playerRecUp = new Rectangle();
    private Rectangle playerRec = new Rectangle();
    private Rectangle playerRecDown = new Rectangle();

    // tiled
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;
    private Rectangle[] doors;

    // controling
    private static boolean drawImages = true;

    // box2d lights
    private RayHandler rayHandler;
    private PointLight pointLight;
    private ConeLight coneLight;


    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FillViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);

        Box2D.init();
        TBox2D.init(PPM);
        world = new World(new Vector2(0, 0), false);
        b2dr = new Box2DDebugRenderer();

        player = BodyFactory.createBodyAsBox(world, BodyDef.BodyType.DynamicBody, false,
                PLAYER_WIDTH, PLAYER_HEIGHT, 325, 325, 0.5f, 0.4f, 0.6f, 2f, 0f,
                false, (short) 0, (short) 0, (short) 0, null, false);

        playerTexture = new Texture(Gdx.files.internal("images/lightAndDoors/playerImage.png"));
        playerImage = new Sprite(playerTexture);
        playerImage.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);

        box1 = BodyFactory.createBodyAsBox(world, BodyDef.BodyType.StaticBody, true,
                (int) (PLAYER_WIDTH * 1.5f), (int) (PLAYER_HEIGHT * 1.5f), 150, 150, 0.5f, 0.4f, 0.6f, 0f, 0f, false, (short) 0, (short) 0, (short) 0, null, false);
        box2 = BodyFactory.createBodyAsBox(world, BodyDef.BodyType.StaticBody, true,
                (int) (PLAYER_WIDTH * 1.5f), (int) (PLAYER_HEIGHT * 1.5f), 150, 500, 0.5f, 0.4f, 0.6f, 0f, 0f, false, (short) 0, (short) 0, (short) 0, null, false);
        box3 = BodyFactory.createBodyAsBox(world, BodyDef.BodyType.StaticBody, true,
                (int) (PLAYER_WIDTH * 1.5f), (int) (PLAYER_HEIGHT * 1.5f), 500, 150, 0.5f, 0.4f, 0.6f, 0f, 0f, false, (short) 0, (short) 0, (short) 0, null, false);
        box4 = BodyFactory.createBodyAsBox(world, BodyDef.BodyType.StaticBody, true,
                (int) (PLAYER_WIDTH * 1.5f), (int) (PLAYER_HEIGHT * 1.5f), 500, 500, 0.5f, 0.4f, 0.6f, 0f, 0f, false, (short) 0, (short) 0, (short) 0, null, false);

        rayHandler = new RayHandler(world);
        rayHandler.setShadows(true);
        rayHandler.setAmbientLight(0.5f); // between 0 and 1
//        pointLight = new PointLight(rayHandler, 100,
//                new Color(46f / 255f, 142f / 255f, 232f / 255f, 1f), 500 / PPM, 150 / PPM, 150 / PPM);

        coneLight = new ConeLight(rayHandler, 120, Color.WHITE, 250 / PPM, 375 / PPM, 375 / PPM, player.getAngle(), 30f);
        coneLight.attachToBody(player);

        map = new TmxMapLoader().load("tiledMaps/lightAndDoors/myMap2.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
        tmr.setView(camera);

        TiledParser.parseTiledObjectLayer(world, map.getLayers().get("collisionLayer").getObjects(), null);

        doors = new Rectangle[] {
                new Rectangle(4 * 32, 11 * 32, 32, 32),
                new Rectangle(15 * 32, 11 * 32, 32, 32)
        };
    }




    private void handleInput(float delta) {
        vel = player.getLinearVelocity();
        pos = player.getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -MAX_VELOCITY)
            player.applyLinearImpulse(-0.80f, 0, pos.x, pos.y, true);
        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < MAX_VELOCITY)
            player.applyLinearImpulse(0.80f, 0, pos.x, pos.y, true);
        if (Gdx.input.isKeyPressed(Input.Keys.W) && vel.y > -MAX_VELOCITY)
            player.applyLinearImpulse(0, 0.80f, pos.x, pos.y, true);
        if (Gdx.input.isKeyPressed(Input.Keys.S) && vel.y < MAX_VELOCITY)
            player.applyLinearImpulse(0, -0.80f, pos.x, pos.y, true);

        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) drawImages = !drawImages;


        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            playerPos = player.getPosition();
            playerRec = new Rectangle(playerPos.x * PPM, playerPos.y * PPM, PLAYER_WIDTH, PLAYER_HEIGHT);
            playerRecUp = new Rectangle(playerPos.x * PPM, playerPos.y * PPM + PLAYER_HEIGHT * 3 / 4, PLAYER_WIDTH, PLAYER_HEIGHT);
            playerRecDown = new Rectangle(playerPos.x * PPM, playerPos.y * PPM - PLAYER_HEIGHT * 3 / 4, PLAYER_WIDTH, PLAYER_HEIGHT);

            for (Rectangle doorRec : doors) {
                if (doorRec.overlaps(playerRecUp) || doorRec.overlaps(playerRecDown)) {
                    if (doorRec.y >= playerRec.y)
                        player.setTransform(playerPos.x, (doorRec.y + doorRec.height + 3) / PPM, player.getAngle());
                    else
                        player.setTransform(playerPos.x, (doorRec.y - doorRec.height - 3) / PPM, player.getAngle());
                }
            }
        }

        if( Gdx.input.isKeyJustPressed(Input.Keys.T))
            player.applyAngularImpulse(1f, false);

    }



    private void update(float deltaTime){
        handleInput(delta);
        world.step(1f / 60f, 6, 2);
        TBox2D.moveSprite(player, playerImage);
    }


    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        TScreenUtils.clearScreen();

        if (!drawImages) {
            rayHandler.updateAndRender();
            b2dr.render(world, camera.combined.cpy().scl(PPM));
        } else {
            tmr.render();

            batch.begin();
            batch.draw(playerTexture, player.getPosition().x * PPM - playerTexture.getWidth() / 2, player.getPosition().y * PPM - playerTexture.getHeight() / 2);
            batch.end();

            rayHandler.updateAndRender();
        }


    }



    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        rayHandler.setCombinedMatrix(camera.combined.cpy().scl(PPM));
        tmr.setView(camera);
    }



}
