package com.telek.telekgdx.tests;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.*;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.*;
import com.telek.telekgdx.box2d.*;
import com.telek.telekgdx.screens.*;



public class Box2DPolygonTest extends ApplicationAdapter {

    static boolean DEBUG = false;

    SpriteBatch batch;
    Sprite spritePlayer;

    Viewport viewport;
    OrthographicCamera camera;

    Box2DDebugRenderer b2dr;
    World world;
    Body player, refObject;

    final float PPM = 32f;


    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);
        viewport.apply();
        camera.position.set(300, 300, 0);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        TBox2D.init(PPM);
        Box2D.init();

        b2dr = new Box2DDebugRenderer();
        world = new World(new Vector2(0,0), false);

        refObject = BodyFactory.createBodyAsBox(world, BodyType.StaticBody, false, 64, 64, 300, 300, 0.5f, 0.5f, 0.5f,
                0.5f, 0f, false, (short) 0, (short) 0, (short) 0, null, false);

//        createPlayer1AsChainShape();
//        createPlayer2AsChainShape();

        createPlayer1AsPolygonShape();
//        createPlayer2AsPolygonShape();


    }


    private void createPlayer1AsPolygonShape() {
        spritePlayer = new Sprite(new Texture(Gdx.files.internal("polygon.png")));

        float imgWidth = 64f;
        float imgHeight = 64f;

        float[] playerVertices = new float[]{
                (8 - imgWidth / 2) / PPM, (imgHeight / 2- 17) / PPM,
                (40 - imgWidth / 2) / PPM, (imgHeight / 2- 5) / PPM,
                (57 - imgWidth / 2) / PPM, (imgHeight / 2- 38) / PPM,
                (38 - imgWidth / 2) / PPM, (imgHeight / 2- 41) / PPM,
                (31 - imgWidth / 2) / PPM, (imgHeight / 2- 26) / PPM,
                (7 - imgWidth / 2) / PPM, (imgHeight / 2- 57) / PPM,
                (8 - imgWidth / 2) / PPM, (imgHeight / 2- 17) / PPM
        };

        Polygon shape = new Polygon(playerVertices);

        EarClippingTriangulator earClippingTriangulator = new EarClippingTriangulator();
        ShortArray triangles = earClippingTriangulator.computeTriangles(playerVertices);


        BodyDef bdef = new BodyDef();
        bdef.type = BodyType.DynamicBody;
        float bodyPosXInPixels = 0;
        float bodyPosYInPixels = 0;
        bdef.position.set((bodyPosXInPixels + imgWidth / 2) / PPM, (bodyPosYInPixels + imgHeight / 2) / PPM);

        player = world.createBody(bdef);

        float[] vertices = shape.getVertices();
        float[] _triangles = new float[6];

        for (int i = 0; i < triangles.size; i += 3)
        {
            for (int j = 0; j < 3; j++) {
                short point = triangles.get(i + j);

                _triangles[j * 2] = vertices[point * 2];
                _triangles[j * 2 + 1] = vertices[point * 2 + 1];
            }

            PolygonShape polygonShape = new PolygonShape();
            polygonShape.set(_triangles);
            player.createFixture(polygonShape, 0.0f);
            polygonShape.dispose();
        }

    }



    private void createPlayer2AsPolygonShape() {

    }



    private void createPlayer1AsChainShape() {
        spritePlayer = new Sprite(new Texture(Gdx.files.internal("polygon.png")));

        float imgWidth = 64f;
        float imgHeight = 64f;

        short s = 0;
        Vector2[] playerVertices = new Vector2[]{
                new Vector2((8 - imgWidth / 2) / PPM, (imgHeight / 2- 17) / PPM),
                new Vector2((40 - imgWidth / 2) / PPM, (imgHeight / 2- 5) / PPM),
                new Vector2((57 - imgWidth / 2) / PPM, (imgHeight / 2- 38) / PPM),
                new Vector2((38 - imgWidth / 2) / PPM, (imgHeight / 2- 41) / PPM),
                new Vector2((31 - imgWidth / 2) / PPM, (imgHeight / 2- 26) / PPM),
                new Vector2((7 - imgWidth / 2) / PPM, (imgHeight / 2- 57) / PPM),
                new Vector2((8 - imgWidth / 2) / PPM, (imgHeight / 2- 17) / PPM)
        };


//        player = BodyFactory.createBodyAsBox(world, BodyType.DynamicBody, false, 64, 64, 0, 0, 0.5f,0.5f,0.5f,0.5f,0f, false, s,s,s,null, false);

        player = BodyFactory.createBodyAsChainShape(world, BodyType.DynamicBody, false,
                128, 64, 0, 0, playerVertices, 0.5f, 0f, 0.5f,
                1f, 0f, false, s, s, s, null, false);


    }



    private void createPlayer2AsChainShape() {
        spritePlayer = new Sprite(new Texture(Gdx.files.internal("polygon2.png")));

        float imgWidth = 128f;
        float imgHeight = 64f;

        short s = 0;
        Vector2[] playerVertices = new Vector2[]{
                new Vector2( (8 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  46) / PPM ),
                new Vector2( (35 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  56) / PPM ),
                new Vector2( (52 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  34) / PPM ),
                new Vector2( (76 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  59) / PPM ),
                new Vector2( (89 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  35) / PPM ),
                new Vector2( (107 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  59) / PPM ),
                new Vector2( (114 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  12) / PPM ),
                new Vector2( (37 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  7) / PPM ),
                new Vector2( (8 - imgWidth / 2f) / PPM , ( imgHeight / 2f -  46) / PPM )
        };


//        player = BodyFactory.createBodyAsBox(world, BodyType.DynamicBody, false, 64, 64, 0, 0, 0.5f,0.5f,0.5f,0.5f,0f, false, s,s,s,null, false);

        player = BodyFactory.createBodyAsChainShape(world, BodyType.DynamicBody, false,
                128, 64, 0, 0, playerVertices, 0.5f, 0f, 0.5f,
                1f, 0f, false, s, s, s, null, false);


    }


    final float moveAmount = 500f;

    private void update(float deltaTime) {
        world.step(1f/60f, 6, 2);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.isKeyJustPressed(Input.Keys.B))
            DEBUG = !DEBUG;

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.applyForceToCenter(-moveAmount / PPM, 0f, true);

        if(Gdx.input.isKeyPressed(Input.Keys.D))
            player.applyForceToCenter(moveAmount / PPM, 0f, true);

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            player.applyForceToCenter(0f, moveAmount / PPM, true);

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.applyForceToCenter(0f, -moveAmount / PPM, true);

        TBox2D.moveAndRotateSprite(player, spritePlayer);
    }


    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        TScreenUtils.clearScreen();

        if(DEBUG)
            b2dr.render(world, camera.combined.cpy().scl(PPM));
        else{
            batch.begin();
            spritePlayer.draw(batch);
            batch.end();
        }

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }


}
