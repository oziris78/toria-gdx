package com.telek.telekgdx.simple.box2d;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class CreatingBodiesTest extends ApplicationAdapter {
    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    @Override
    public void create () {
        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera(50, 25);
        debugRenderer = new Box2DDebugRenderer();

        // ground
        createEdge(BodyDef.BodyType.StaticBody, -20, -10f, 20, -10f, 0);
        // left wall
        createEdge(BodyDef.BodyType.StaticBody, -20, -10, -20, 10, 0);
        // right wall
        createEdge(BodyDef.BodyType.StaticBody, 20, -10, 20, 10, 0);

        createCircle(BodyDef.BodyType.DynamicBody, 0, 0, 1, 3);

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {

                Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchedPoint);

                if(MathUtils.randomBoolean()) {
                    createBox(BodyDef.BodyType.DynamicBody, touchedPoint.x, touchedPoint.y, 1, 1, 1);
                }
                else{
                    createCircle(BodyDef.BodyType.DynamicBody, touchedPoint.x, touchedPoint.y, 1, 3);
                }

                return true;
            }
        });
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(.125f, .125f, .125f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined);
        world.step(1 / 60f, 6, 2);
    }

    @Override
    public void dispose () {
        world.dispose();
        debugRenderer.dispose();
    }

    private Body createBox(BodyDef.BodyType type, float x, float y, float width, float height, float density) {
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        poly.dispose();

        return body;
    }

    private Body createEdge(BodyDef.BodyType type, float x1, float y1, float x2, float y2, float density) {
        EdgeShape poly = new EdgeShape();
        poly.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x1, y1, 0);
        poly.dispose();

        return body;
    }

    private Body createCircle(BodyDef.BodyType type, float x, float y, float radius, float density) {
        CircleShape poly = new CircleShape();
        poly.setRadius(radius);

        BodyDef def = new BodyDef();
        def.type = type;
        Body body = world.createBody(def);
        body.createFixture(poly, density);
        body.setTransform(x, y, 0);
        poly.dispose();

        return body;
    }
}