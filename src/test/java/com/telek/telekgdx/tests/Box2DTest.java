package com.telek.telekgdx.tests;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.telek.telekgdx.box2d.BodyFactory;
import com.telek.telekgdx.box2d.TBox2D;

public class Box2DTest extends ApplicationAdapter {
    // consts
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;

    // box2d
    World world;
    Box2DDebugRenderer b2dr;
    Body player;
    final float PPM = 32f;
    float moveAmount = 2f;

    // rendering objects
    OrthographicCamera camera;
    ShapeRenderer sr;
    Viewport viewport;
    private final Array<Body> bodies = new Array<>();


    @Override
    public void create() {
        // rendering objects
        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, this.camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        sr = new ShapeRenderer();

        // box2d
        Box2D.init();
        TBox2D.init(PPM);
        b2dr = new Box2DDebugRenderer();
        world = new World(Vector2.Zero, false);


        final short s = 0;
        player = BodyFactory.createBodyAsBox(
                world, BodyDef.BodyType.DynamicBody, true, 32, 32, 0, 0, 1f, 1f, 1f, 1f, 0f, false, s, s, s, null, false
        );


        bodies.add(player);



        camera.update();
    }



    public void update() {
        world.step(1f / 60f, 6, 2);

        handleCameraZoomInput();
        handlePlayerBodyInput();

        camera.update();
        sr.setProjectionMatrix(camera.combined);
    }



    @Override
    public void render() {
        update();

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 1);

        b2dr.render(world, camera.combined.cpy().scl(PPM));

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(0, 0, 32, 32);
        sr.end();
    }






    private void handleCameraZoomInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) camera.zoom -= 0.2f;
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) camera.zoom += 0.2f;
    }


    private void handlePlayerBodyInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) player.applyForceToCenter(+moveAmount, 0f, true);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) player.applyForceToCenter(-moveAmount, 0f, true);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) player.applyForceToCenter(+0f, +moveAmount, true);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) player.applyForceToCenter(+0f, -moveAmount, true);
    }


}
