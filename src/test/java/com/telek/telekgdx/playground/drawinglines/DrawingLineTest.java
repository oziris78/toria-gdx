package com.telek.telekgdx.playground.drawinglines;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.telek.telekgdx.box2d.BodyFactory;
import com.telek.telekgdx.box2d.TBox2D;
import com.telek.telekgdx.screens.TScreenUtils;

import java.util.ArrayList;


public class DrawingLineTest extends ApplicationAdapter implements InputProcessor {

    ShapeRenderer sr;

    final int TILE_SIZE = 1;
    Vector2 firstPoint = new Vector2(-20f, -20f);
    Vector2 secondPoint = new Vector2(-20f, -20f);
    Vector2[] linePoints = null;
    int settingOrder = 0;


    @Override
    public void create() {
        sr = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void render() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(firstPoint.x >= 0f && firstPoint.y >= 0f && secondPoint.x >= 0f && secondPoint.y >= 0f) {
                linePoints = line(firstPoint, secondPoint);
            }
        }

        super.render();
        TScreenUtils.clearScreen();


        sr.begin(ShapeRenderer.ShapeType.Filled);

        if(firstPoint.x >= 0f && firstPoint.y >= 0f) {
            sr.setColor(Color.YELLOW);
            sr.rect(firstPoint.x, firstPoint.y, TILE_SIZE, TILE_SIZE);
        }
        if(secondPoint.x >= 0f && secondPoint.y >= 0f) {
            sr.setColor(Color.RED);
            sr.rect(secondPoint.x, secondPoint.y, TILE_SIZE, TILE_SIZE);
        }
        if( linePoints != null){
            sr.setColor(Color.WHITE);
            for(Vector2 vec : linePoints)
                sr.rect(vec.x, vec.y, TILE_SIZE, TILE_SIZE);
        }

        sr.end();
    }


    private static Vector2[] line(Vector2 firstPoint, Vector2 secondPoint) {
        float N = diagonal_distance(firstPoint, secondPoint);
        ArrayList<Vector2> list = new ArrayList<>();
        for (int step = 0; step <= N; step++) {
            float t = (N == 0) ? 0f : (step / N);
            list.add(round_point(lerp_point(firstPoint, secondPoint, t)));
        }
        Vector2[] actualList = new Vector2[list.size()];
        int i = 0;
        for(Vector2 vec : list){
            actualList[i] = vec;
            i++;
        }
        return actualList;
    }


    private static float diagonal_distance(Vector2 firstPoint, Vector2 secondPoint) {
        return Math.max(Math.abs(secondPoint.x - firstPoint.x), Math.abs(secondPoint.y - firstPoint.y));
    }

    private static Vector2 round_point(Vector2 vector) {
        return new Vector2(Math.round(vector.x), Math.round(vector.y));
    }

    private static float lerp(float start, float end, float t) {
        return start + t * (end-start);
    }

    private static Vector2 lerp_point(Vector2 firstPoint, Vector2 secondPoint, float t) {
        return new Vector2(lerp( (int) firstPoint.x, (int) secondPoint.x, t), lerp(firstPoint.y, secondPoint.y, t));
    }




    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        settingOrder++;
        screenY = Gdx.graphics.getHeight() - screenY;
        screenX -= screenX % TILE_SIZE;
        screenY -= screenY % TILE_SIZE;
        if(settingOrder % 2 == 0) firstPoint.set(screenX, screenY);
        else secondPoint.set(screenX, screenY);
        return true;
    }


    // unuswed
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    @Override public boolean keyTyped(char character) {return false;}
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
}
