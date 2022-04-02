package com.telek.telekgdx.simple.cameras;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CameraTest2 extends ApplicationAdapter {

    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    float rectWidth = 100;
    float rectHeight = 50;
    float moveSpeed = 100;
    float rotateSpeed = 20;
    float zoomSpeed = 1;

    @Override
    public void create() {
        camera = new OrthographicCamera(200, 100);
        camera.position.set(0, 0, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {

        // move camera left and right
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, moveSpeed * Gdx.graphics.getDeltaTime());
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -moveSpeed * Gdx.graphics.getDeltaTime());
        }

        // move camera up and down
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-moveSpeed * Gdx.graphics.getDeltaTime(), 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(moveSpeed * Gdx.graphics.getDeltaTime(), 0);
        }

        // zoom camera
        if(Gdx.input.isKeyPressed((Input.Keys.UP))){
            camera.zoom -= zoomSpeed * Gdx.graphics.getDeltaTime();
        }
        else if(Gdx.input.isKeyPressed((Input.Keys.DOWN))){
            camera.zoom += zoomSpeed * Gdx.graphics.getDeltaTime();
        }

        // rotate camera
        if(Gdx.input.isKeyPressed((Input.Keys.LEFT))){
            camera.rotate(-rotateSpeed * Gdx.graphics.getDeltaTime());
        }
        else if(Gdx.input.isKeyPressed((Input.Keys.RIGHT))){
            camera.rotate(rotateSpeed * Gdx.graphics.getDeltaTime());
        }

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(-rectWidth, -rectHeight, rectWidth, rectHeight);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(0, -rectHeight, rectWidth, rectHeight);
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.rect(0, 0, rectWidth, rectHeight);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.rect(-rectWidth, 0, rectWidth, rectHeight);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}