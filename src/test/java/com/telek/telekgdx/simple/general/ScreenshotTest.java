package com.telek.telekgdx.simple.general;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenshotTest extends ApplicationAdapter {


    SpriteBatch batch;
    BitmapFont font;


    @Override
    public void create() {
        super.create();
        font = new BitmapFont();
        batch = new SpriteBatch();

        System.out.println(Gdx.files.getExternalStoragePath());
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.TAB){ // TAB'a basılırsa işlemi yap

                byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(),
                        Gdx.graphics.getBackBufferHeight(), true);

                // This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
                for (int i = 4; i <= pixels.length; i += 4) {
                    pixels[i - 1] = (byte) 255;
                }

                Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(),
                        Pixmap.Format.RGBA8888);
                BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
                PixmapIO.writePNG(Gdx.files.external("mypixmap.png"), pixmap);
                pixmap.dispose();
            }
            return true;
            }
        });

    }



    @Override
    public void render() {
        
        super.render();
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        Object obj1 = Gdx.app.getJavaHeap();
        String s1 = String.valueOf( obj1 );
        font.draw(batch,"javaHeap: "+s1,50,50,100, Align.center, false);
        batch.end();


    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
    }
}
