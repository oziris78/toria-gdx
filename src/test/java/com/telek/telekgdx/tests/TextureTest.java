package com.telek.telekgdx.tests;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.telek.telekgdx.screens.*;


public class TextureTest extends ApplicationAdapter {

    SpriteBatch batch;
    OrthographicCamera camera;
    Viewport viewport;

    Texture texture;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(600, 600, this.camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        texture = new Texture(Gdx.files.internal("badlogic.jpg"));


        camera.update();
    }



    public void update() {
        camera.update();
    }



    @Override
    public void render() {
        update();

        TScreenUtils.clearScreen();

        batch.begin();
        batch.draw(texture, 0f, 0f);
        batch.end();
    }





}
