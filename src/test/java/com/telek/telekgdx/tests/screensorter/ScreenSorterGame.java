package com.telek.telekgdx.tests.screensorter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.telek.telekgdx.screens.ScreenSorter;


public class ScreenSorterGame extends Game {

    SpriteBatch batch;
    BitmapFont font;
    ScreenSorter<ScreenSorterGame> screenSorter;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        screenSorter = new ScreenSorter<>(this);
        screenSorter.putScreen("screenOne", ScreenOne.class);
        screenSorter.putScreen("screenTwo", ScreenTwo.class);

        this.setScreen(screenSorter.getScreen("screenOne"));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        screenSorter.dispose();
        font.dispose();
        batch.dispose();
    }

}
