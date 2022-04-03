package com.telek.telekgdx.simple.tiled;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.telek.telekgdx.box2d.BodyFactory;
import com.telek.telekgdx.box2d.TBox2D;
import com.telek.telekgdx.box2d.TiledParser;
import com.telek.telekgdx.tiled.TiledUtils;

public class TiledBodiesTest implements ApplicationListener {

    // consts
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;

    // control
    private static boolean DEBUG = true;

    // tiled
    OrthogonalTiledMapRenderer tmr;
    TiledMap map;

    // box2d
    World world;
    Box2DDebugRenderer b2dr;
    Body player;
    final float PPM = 32f;
    float moveAmount = 2f;

    // rendering objects
    OrthographicCamera camera;
    Viewport viewport;
    private final Array<Body> bodies = new Array<>();


    public void update() {
        world.step(1f / 60f, 6, 2);

        if (Gdx.input.isKeyJustPressed(Keys.B)) DEBUG = !DEBUG;

        handleCameraZoomInput();
        handlePlayerBodyInput();
        moveSprites();

        moveCameraToSprite();
        updateCam();
    }

    private void handleCameraZoomInput() {
        if (Gdx.input.isKeyJustPressed(Keys.R)) camera.zoom -= 0.2f;
        if (Gdx.input.isKeyJustPressed(Keys.T)) camera.zoom += 0.2f;
    }

    private void moveCameraToSprite() {
        camera.position.set(((Sprite) player.getUserData()).getX(), ((Sprite) player.getUserData()).getY(), 0f);
    }

    private void handlePlayerBodyInput() {
        if (Gdx.input.isKeyPressed(Keys.D)) player.applyForceToCenter(+moveAmount, 0f, true);
        if (Gdx.input.isKeyPressed(Keys.A)) player.applyForceToCenter(-moveAmount, 0f, true);
        if (Gdx.input.isKeyPressed(Keys.W)) player.applyForceToCenter(+0f, +moveAmount, true);
        if (Gdx.input.isKeyPressed(Keys.S)) player.applyForceToCenter(+0f, -moveAmount, true);
    }

    private void moveSprites() {
        for (Body b : bodies)
            TBox2D.moveSprite(b, (Sprite) b.getUserData());
    }

    @Override
    public void create() {
        // rendering objects
        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, this.camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        // box2d
        Box2D.init();
        TBox2D.init(PPM);
        b2dr = new Box2DDebugRenderer();
        world = new World(Vector2.Zero, false);


        player = BodyFactory.createBodyAsCircle(
                world, BodyDef.BodyType.DynamicBody, true,
                0f, 0f, 16f, 0.5f, 0.5f, 0.5f, 1f, 0f,
                false, (short) 0, (short) 0, (short) 0,
                new Sprite(new Texture("images/lightAndDoors/playerImage32x32.png")), false
        );

        bodies.add(player);
        moveSprites();

        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("tiledMaps/learningTiled/myCoolMap.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);

        TiledParser.parseTiledObjectLayer(world, map.getLayers().get("collision-layer").getObjects(), null);
        TiledUtils.addAnimatedTileToMap(map, 0.5f, "myCoolTileset", "tile-layer", "blueFlowerAnimation", "heyyo");

        System.out.println(TiledUtils.getHorizontalTileCount(map));
        System.out.println(TiledUtils.getVerticalTileCount(map));
        System.out.println(TiledUtils.getTileWidth(map));

        updateCam();
    }


    @Override
    public void render() {
        update();

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 1);

        if (DEBUG) {
            b2dr.render(world, camera.combined.cpy().scl(PPM));
            return;
        }

//        TiledUtils.updateTiledAnimation();

        tmr.render();

        tmr.getBatch().begin();
        for (Body b : bodies) ((Sprite) b.getUserData()).draw(tmr.getBatch());
        tmr.getBatch().end();
    }


    private void updateCam() {
        camera.update();
        tmr.setView(camera);
        tmr.getBatch().setProjectionMatrix(this.camera.combined);
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
