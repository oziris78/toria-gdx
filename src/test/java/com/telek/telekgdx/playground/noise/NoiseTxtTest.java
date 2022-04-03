package com.telek.telekgdx.playground.noise;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.telek.telekgdx.TelekGDX;
import com.telek.telekgdx.screens.TScreenUtils;
import com.telek.telekmath.advanced.random.TNoise;

import java.io.IOException;
import java.util.Random;

public class NoiseTxtTest implements ApplicationListener {

    static String filePath = "generated/noise_txt_results/test.txt";

    @Override
    public void create() {
        // write the results into a file
        createSomeRandomOutputsAndSaveThem(10);

        // open that file
        try { TelekGDX.openFileExplorer(Gdx.files.local(filePath)); }
        catch (IOException e) { e.printStackTrace(); }

    }


    private void createSomeRandomOutputsAndSaveThem(int limit) {
        Random random = new Random();
        FileHandle file = Gdx.files.local(filePath);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (canonicalRandom): " + TNoise.canonicalRandom(x,y));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (valueNoise): " + TNoise.valueNoise(x,y));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (morganMcguireNoise): " + TNoise.morganMcguireNoise(x,y));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (perlinNoise): " + TNoise.perlinNoise(x,y));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (fbmFilteredPerlinNoise): " + TNoise.fbmFilteredPerlinNoise(x,y));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (brokenPerlinNoise false): " + TNoise.brokenPerlinNoise(x,y, false));
            sb.append("\n");
        }

        for (int i = 0; i < limit; i++) {
            float x = random.nextFloat(), y = random.nextFloat();
            sb.append("x: " + x + " , y: " + y + "  , (brokenPerlinNoise true): " + TNoise.brokenPerlinNoise(x,y, true));
            sb.append("\n");
        }

        file.writeString( sb.toString(), false );
    }



    @Override public void render() { TScreenUtils.clearScreen(); }
    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void dispose() { }
}
