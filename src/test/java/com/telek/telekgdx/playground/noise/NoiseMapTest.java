package com.telek.telekgdx.playground.noise;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.Timer;
import com.telek.telekgdx.TelekGDX;
import com.telek.telekgdx.screens.TScreenUtils;
import com.telek.telekmath.advanced.random.TNoise;
import com.telek.telekmath.utils.TMath;


public class NoiseMapTest implements ApplicationListener {


    private static boolean shouldBeClosed;


    @Override
    public void create() {
        // info
        final int TILE_SIZE = 16;
        final int width = 2000;
        final int height = 2000;
        float scale = 0.1f;

        // create map and open it
        createMapAndOpenIt(width, height, TILE_SIZE, scale);

        // make the window close itself in 2 secs
        shouldBeClosed = true;
    }

    private static String usedFunc = "perlin";

    private void createMapAndOpenIt(int width, int height, int TILE_SIZE, float scale) {
        // files for saving
        int fileID = (int) (Math.random() * 9999);
        FileHandle imageFile = new FileHandle("generated/noise_maps_results/map" + fileID + ".png");
        FileHandle infoFile = new FileHandle("generated/noise_maps_results/map" + fileID + ".txt");

        // fill pixmap with colors
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                float v = (float) TMath.mapRange( TNoise.perlinNoise(i * scale, j * scale) );
                pixmap.setColor( chooseColor(v) );
                pixmap.fillRectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // save pixmap
        PixmapIO.writePNG(imageFile, pixmap);
        pixmap.dispose();

        // save info
        StringBuilder sb = new StringBuilder();
        sb.append("usedFunc = " + usedFunc + "\n");
        sb.append("scale = " + scale + "\n");
        sb.append("width = " + width + "\n");
        sb.append("height = " + height + "\n");
        sb.append("TILE_SIZE = " + TILE_SIZE + "\n");
        infoFile.writeString(sb.toString(), false);

        // open the image you created
        try{ TelekGDX.openFileExplorer(imageFile); }
        catch (Exception e) { e.printStackTrace();}
    }


    private Color chooseColor(float value){
        if(value < 0.3f) return Color.CYAN;
        else if(value < 0.4f) return Color.YELLOW;
        else if(value < 0.7f) return Color.LIME;
        else return Color.GREEN;
    }


    @Override
    public void render() {
        TScreenUtils.clearScreen();

        if(shouldBeClosed) Timer.schedule(new Timer.Task() {@Override public void run() { Gdx.app.exit(); }}, 2);
    }


    @Override public void resize(int width, int height) {}
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void dispose() { }
}
