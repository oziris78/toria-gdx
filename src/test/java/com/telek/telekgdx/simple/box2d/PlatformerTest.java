package com.telek.telekgdx.simple.box2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PlatformerTest extends ApplicationAdapter {

    // box2d consts
    private static final float PPM = 4; // pixels per meter
    private static final float SPEED_INCREMENT = 35;
    private static final float MAX_VELOCITY = SPEED_INCREMENT*1.10f;
    private static final float TIME_STEP = 1/50f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final Vector2 gravityVector = new Vector2(0,-98f);
    // general consts
    private static final float FRAME_HEIGHT = 64 * 4;
    private static final float FRAME_WIDTH = 48 * 4;
    // game objects
    static World world;
    static Box2DDebugRenderer b2dr;
    static Body player;
    static Body ground;
    static SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    // timing and saving CPU from creating objects stuff
    private float deltaTime;
    private float frameTime;
    private float accumulator = 0;
    private Vector2 vel;
    private Vector2 pos;
    private boolean canJump = true;


    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();

        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        camera.position.set(FRAME_WIDTH / 2 /PPM, FRAME_HEIGHT / 2 /PPM, 0);
        camera.update();
        viewport = new StretchViewport(FRAME_WIDTH*aspectRatio,FRAME_HEIGHT, camera);
        viewport.apply();
        camera.update();

        batch.setProjectionMatrix(camera.combined);


        Box2D.init();
        b2dr = new Box2DDebugRenderer();
        world = new World(gravityVector, true);
        player = createPlayer();
        ground = createGround();

    }

    private Body createGround() {
        BodyDef groundBodyDef = new BodyDef();  // Create our body definition
        groundBodyDef.position.set(0/PPM, 10/PPM);   // Set its world position
        Body groundBody = world.createBody(groundBodyDef);  // Create a body from the definition and add it to the world
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth/PPM, 10.0f/PPM);
        groundBody.createFixture(groundBox, 0.0f);  // Create a fixture from our polygon shape and add it to our ground body
        groundBox.dispose();
        return groundBody;
    }



    private Body createPlayer() {
        BodyDef bodyDef = new BodyDef(); // First we create a body definition
        bodyDef.type = BodyType.DynamicBody; // We set our body to dynamic
        bodyDef.position.set(50/PPM, 200/PPM); // Set our body's starting position in the world
        Body body = world.createBody(bodyDef); // Create our body in the world using our body definition
        CircleShape circle = new CircleShape();  // Create a circle shape
        circle.setRadius(10f/PPM); // set its radius to 10
        FixtureDef fixtureDef = new FixtureDef(); // Create a fixture definition to apply our shape to
        fixtureDef.shape = circle;
        fixtureDef.density = 0.3f;  // density tanımı
        fixtureDef.friction = 0.3f;  // friction tanımı
        fixtureDef.restitution = 0.4f;  // restitution tanımı
        Fixture fixture = body.createFixture(fixtureDef); // Create our fixture and attach it to the body
        circle.dispose();  // Remember to dispose of any shapes after you're done with them!
        return body;
    }


    private void update(float deltaTime) {
        updateBox2dWorld(deltaTime);
        handleInput(deltaTime);

    }

    private void handleInput(float deltaTime) {
        vel = player.getLinearVelocity();
        pos = player.getPosition();

        if (Gdx.input.isKeyPressed(Input.Keys.A) && vel.x > -MAX_VELOCITY) {
            player.applyLinearImpulse(-SPEED_INCREMENT, 0, pos.x, pos.y, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && vel.x < MAX_VELOCITY) {
            player.applyLinearImpulse(SPEED_INCREMENT, 0, pos.x, pos.y, true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && canJump) {
            player.applyForceToCenter(0f, gravityVector.y * (-1f) * 200, true);
            canJump = !canJump;
            Timer.schedule(new Timer.Task() { @Override public void run() { canJump = !canJump; }}, 1.15f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            player.applyForceToCenter(0f, -gravityVector.y, true);
        }

    }


    private void updateBox2dWorld(float deltaTime) {
        frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void render() {
        super.render();
        deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, camera.combined);

        batch.begin();


        batch.end();
    }


    @Override
    public void resize(int w, int h) { super.resize(w, h); updateCamera(w,h);}
    @Override
    public void dispose() { super.dispose(); }

    private void updateCamera(int width, int height){
        viewport.update(width, height, false);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
    private void updateCamera(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
