package com.telek.telekgdx.simple.pixmaps;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.telek.telekgdx.screens.TScreenUtils;

public class PixmapDrawingTest extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;

    Texture texture;
    Pixmap pixmap;

    FileHandle file;

    @Override
    public void create() {

        batch = new SpriteBatch();

        pixmap = new Pixmap( Gdx.files.internal("badlogic.jpg") );
        texture = new Texture( pixmap );

        file = Gdx.files.local("generated/pixmap_drawing_results/pixmap.cim");

        Gdx.input.setInputProcessor(this);



    }

    @Override
    public void render() {
        super.render();
        TScreenUtils.clearScreen();

        batch.begin();
        if(texture != null) batch.draw(texture, 0, 0);
        batch.end();
    }




    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        pixmap.setColor(Color.GREEN);
        pixmap.fillCircle(screenX, screenY - (Gdx.graphics.getHeight() - pixmap.getHeight()), 5);
        texture.draw(pixmap, 0, 0);
        return true;
    }


    @Override public boolean keyTyped(char character) {
        char typedChar = Character.toLowerCase(character);
        if( typedChar == 's' ){ // SAVING
            PixmapIO.writeCIM(file, pixmap);
            return true;
        }
        else if( typedChar == 'l'){ // LOADING
            pixmap.dispose();
            if(file.exists()) {
                pixmap = PixmapIO.readCIM(file);
                texture.draw(pixmap, 0, 0);
            }
            else System.out.println("file doesn't exist");
            return true;
        }
        else if( typedChar == 'e' ){ // EXPORT AS PNG
            PixmapIO.writePNG(Gdx.files.local("generated/pixmap_drawing_results/pixmapImage" + ((int) Math.random() * 1000) + ".png"), pixmap);
            return true;
        }

        return false;
    }


    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
}
