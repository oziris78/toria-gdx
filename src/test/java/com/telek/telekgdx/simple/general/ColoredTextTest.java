package com.telek.telekgdx.simple.general;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.util.Map;


public class ColoredTextTest extends ApplicationAdapter{

    private static final float FRAME_BOYU = 300;
    private static final float FRAME_ENI = 300;
    Camera camera;
    Viewport viewport;
    BitmapFont font20;
    BitmapFont font50;
    SpriteBatch batch;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();

        long beforeFont = Gdx.app.getJavaHeap();
        font20 = prepareFont(font20, 20, "./font/audiowide.ttf");
        font50 = prepareFont(font50, 50, "./font/audiowide.ttf");
        long afterFont = Gdx.app.getJavaHeap();
        System.out.println("2 fonts take this much space: " + (afterFont  -beforeFont ) * 0.000001 );

        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FitViewport( FRAME_ENI * aspectRatio, FRAME_BOYU);
        viewport.apply();
        camera.position.set(FRAME_ENI / 2, FRAME_BOYU / 2, 0);
        camera.position.setZero();
        camera.update();

    }


    @Override
    public void render() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        batch.begin();
        font20.draw(batch, "[BLUE]M[RED]u[YELLOW]l[GREEN]t[OLIVE]ic[]o[]l[]o[]r[]*[MAROON]Label[][] ", 50, 50);
        batch.end();


    }

    private BitmapFont prepareFont(BitmapFont fontRef, int size, String path){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = Color.WHITE;
        parameter.borderColor = new Color(0.1f, 0.1f, 0.1f, 0.8f);
        parameter.borderWidth = 1f;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0.1f, 0.1f, 0.1f, 0.8f);
        fontRef = generator.generateFont(parameter);
        fontRef.getData().markupEnabled = true;
        fontRef.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();
        return fontRef;
    }


}

