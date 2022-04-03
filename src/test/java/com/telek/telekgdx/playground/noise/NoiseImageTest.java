package com.telek.telekgdx.playground.noise;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.telek.telekgdx.TelekGDX;
import com.telek.telekgdx.screens.TScreenUtils;
import com.telek.telekmath.advanced.random.TNoise;
import com.telek.telekmath.utils.TMath;

import java.io.IOException;

public class NoiseImageTest implements ApplicationListener {


    @Override
    public void create() {

        final int width = 512;
        final int height = 512;
        createImagesAndSaveThem(width, height, 0.01f);
        createImagesAndSaveThem(width, height, 0.02f);
        createImagesAndSaveThem(width, height, 0.04f);
        createImagesAndSaveThem(width, height, 0.08f);
        createImagesAndSaveThem(width, height, 0.16f);
        createImagesAndSaveThem(width, height, 0.5f);
        createImagesAndSaveThem(width, height, 1f);
        createImagesAndSaveThem(width, height, 2f);
        createImagesAndSaveThem(width, height, 3f);
        try { TelekGDX.openFileExplorer(Gdx.files.local("generated/noise_img_results")); }
        catch (IOException e) { e.printStackTrace(); }

    }

    private void createImagesAndSaveThem(int width, int height, float scale) {
        int fileNo = 0;
        String folderName = "generated/noise_img_results/noiseImagesScale" + scale;

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = TNoise.canonicalRandom(row * scale, col * scale);
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/canonical" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }


        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = TNoise.morganMcguireNoise(row, col);
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/morganMcguireNoise" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }


        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = TNoise.valueNoise(row * 2, col * 2);
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/valueNoise" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.perlinNoise(row * scale, col * scale) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/perlinNoise" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = TNoise.brokenPerlinNoise(row * scale, col * scale, false);
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/brokenPerlinNoiseFalse" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = TNoise.brokenPerlinNoise(row * scale, col * scale, true);
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/brokenPerlinNoiseTrue" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise4octaveDeff" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale, 1) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise1octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale, 2) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise2octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale, 3) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise3octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale, 5) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise5octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * scale, col * scale, 6) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise6octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }

        {
            Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            for (int row = 0; row < width; row++) {
                for (int col = 0; col < height; col++) {
                    float c = (float) TMath.mapRange( TNoise.fbmFilteredPerlinNoise(row * 0.01f, col * 0.01f, 7) );
                    pixmap.setColor(c, c, c, 1f);
                    pixmap.fillRectangle(row, col, 1, 1);
                }
            }
            FileHandle file = Gdx.files.local(folderName + "/fbmFilteredPerlinNoise7octave" + fileNo++ + ".png");
            PixmapIO.writePNG(file, pixmap);
        }


    }



    @Override
    public void render() {
        TScreenUtils.clearScreen();

    }



    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void dispose() { }
}
