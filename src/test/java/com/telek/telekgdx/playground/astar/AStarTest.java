package com.telek.telekgdx.playground.astar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class AStarTest implements ApplicationListener {

    // rendering
    ShapeRenderer shapeRenderer;
    OrthographicCamera camera;
    Viewport viewport;

    // ai
    GridAI grid;


    @Override
    public void create() {
        // rendering
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(300, 300, this.camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        grid = new GridAI(0, 0, 10, 10, 50);
        grid.colorShortestPath(2, 2, 8, 8);

    }


    private void update(float delta) {

    }


    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        grid.draw(shapeRenderer);
        shapeRenderer.end();


    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }



    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void dispose() { }
}