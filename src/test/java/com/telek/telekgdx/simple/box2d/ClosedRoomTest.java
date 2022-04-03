package com.telek.telekgdx.simple.box2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ClosedRoomTest extends ApplicationAdapter {

    // consts
    public static final int PPM = 32;
    public static final int WORLD_WIDTH = 50;
    public static final int WORLD_HEIGHT = 50;
    public static final Vector2 vecGravity = new Vector2(0f, 0f);
    public static final float worldStep = 1f / 60f;
    public static final int velocityIterations = 6;
    public static final int positionIterations = 2;
    public static final int PLAYER_WIDTH = 128;
    public static final int PLAYER_HEIGHT = 128;


    // general objects
    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;
    Texture badlogic;

    // box2d
    Body player;
    Body ball;
    World world;
    Body[] walls;
    Box2DDebugRenderer b2dr;

    // timing
    private float delta; // general delta taken from render()
    private float accumulator; // for updateWorld()

    // empty objects for optimization
    Vector2 curPos;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        badlogic = new Texture(Gdx.files.internal("badlogic.jpg"));

        camera = new OrthographicCamera();
        viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        batch.setProjectionMatrix(camera.combined);

        Box2D.init();
        world = new World(vecGravity, true);
        b2dr = new Box2DDebugRenderer();
        player = createBody(20, 20, PLAYER_WIDTH, PLAYER_HEIGHT, 0.75f);
        player.setFixedRotation(true);
        ball = createBody(10, 10, PLAYER_WIDTH, PLAYER_HEIGHT, 0.75f);
        walls = new Body[4];
        createWalls(walls);
    }


    private void update(float delta) {
        updateWorld(delta);
    }


    @Override
    public void render() {
        super.render();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        delta = Gdx.graphics.getDeltaTime();
        update(delta);
        handleInputs(delta);
        b2dr.render(world, camera.combined);

        batch.begin();
        drawPlayer(batch);
        batch.end();

    }

    private void handleInputs(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.applyForceToCenter(-1000f, 0.0f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.applyForceToCenter(1000f, 0.0f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.applyForceToCenter(0.0f, 1000f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.applyForceToCenter(0.0f, -1000f, true);
        }


    }

    /*  HELPER FUNCTION  */

    // updates the world using delta time instead of timeStep all the time
    private void updateWorld(float delta) {
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= worldStep) {
            world.step(worldStep, velocityIterations, positionIterations);
            accumulator -= worldStep;
        }
    }

    // creates box2d body for player
    private Body createBody(int x, int y, int w, int h, float density) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2 / PPM, h / 2 / PPM);
        body.createFixture(shape, density);
        shape.dispose();
        return body;
    }

    private void createWalls(Body[] walls) {
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, 0);
            walls[0] = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(WORLD_WIDTH + 1f, 0.1f);
            walls[0].createFixture(shape, 0f);
            shape.dispose();
        }
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(WORLD_WIDTH, 0);
            walls[1] = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.1f, WORLD_HEIGHT + 1f);
            walls[1].createFixture(shape, 0f);
            shape.dispose();
        }
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, WORLD_HEIGHT);
            walls[2] = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(WORLD_WIDTH + 1f, 0.1f);
            walls[2].createFixture(shape, 0f);
            shape.dispose();
        }
        {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, 0);
            walls[3] = world.createBody(bodyDef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.1f, WORLD_HEIGHT + 1f);
            walls[3].createFixture(shape, 0f);
            shape.dispose();
        }


    }

    private void drawPlayer(SpriteBatch batch) {
        curPos = player.getPosition().sub(PLAYER_WIDTH / PPM / 2, PLAYER_HEIGHT / PPM / 2);
        batch.draw(badlogic, curPos.x, curPos.y, PLAYER_WIDTH / PPM, PLAYER_HEIGHT / PPM);
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        world.dispose();
        b2dr.dispose();
    }


}
