package com.telek.telekgdx.simple.images;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.telek.telekgdx.screens.TScreenUtils;

public class SpriteMovementTest implements ApplicationListener {

    private SpriteBatch sb;
    private OrthographicCamera cam;
    private Texture texture;
    private Sprite sprite;


    @Override
    public void create() {
        sb = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 5f, 5f);

        Pixmap pixmap = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        texture = new Texture(pixmap);
        // you can use sprite if you want
        sprite = new Sprite(texture);
        sprite.setPosition(2,2);
        sprite.setSize(0.5f,0.5f);
    }




    private void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            sprite.translate(0.05f, 0f);

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            sprite.translate(-0.05f, 0f);

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            sprite.translate(0f, 0.05f);

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            sprite.translate(0f, -0.05f);
    }





    @Override
    public void render(){
        //buffer screen
        TScreenUtils.clearScreen();
        sb.setProjectionMatrix(cam.combined);

        //rendering
        handleInput();


        sb.begin();

        sprite.draw(sb);
        sprite.rotate(0.5f);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);

        sb.draw(texture, 1f, 1f, 1f, 1f);
        sb.end();
    }



    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void dispose(){}

}
