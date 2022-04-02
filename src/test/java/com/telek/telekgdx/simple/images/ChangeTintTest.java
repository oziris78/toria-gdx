package com.telek.telekgdx.simple.images;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class ChangeTintTest extends ApplicationAdapter {

    final float WORLD_WIDTH = 600;
    final float WORLD_HEIGHT = 600;

    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    TextureRegion textureRegion;
    OrthographicCamera camera;


    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(texture, 0,0,texture.getWidth(), texture.getHeight());
        sprite.setPosition(0,0);
        textureRegion = new TextureRegion(texture, 0,0,texture.getWidth(), texture.getHeight());
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
        camera.update();
    }

    @Override
    public void render() {
        super.render();



        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(0,0,WORLD_WIDTH/2,WORLD_HEIGHT);
        ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
        if (ScissorStack.pushScissors(scissors)) {

            batch.begin();
            batch.setColor(Color.YELLOW);
            batch.draw(texture,300,0,texture.getWidth(), texture.getHeight());
            batch.setColor(Color.CYAN);
            batch.draw(textureRegion,0,300,texture.getWidth(), texture.getHeight());
            sprite.setColor(Color.RED);
            sprite.draw(batch);
            batch.end();

            batch.flush();
            ScissorStack.popScissors();
        }



    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
