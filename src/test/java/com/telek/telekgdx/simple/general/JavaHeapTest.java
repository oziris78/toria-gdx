package com.telek.telekgdx.simple.general;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JavaHeapTest extends ApplicationAdapter {

    BitmapFont font;
    SpriteBatch batch;

    @Override
    public void create() {
        super.create();
        font = new BitmapFont();
        batch = new SpriteBatch();
        font.getData().setScale(3f);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.R))
            new Texture(Gdx.files.internal("badlogic.jpg"));

        batch.begin();
        font.draw(batch, "javaHeap:" + Gdx.app.getJavaHeap(), 50f, 100f);
        batch.end();
    }


}
