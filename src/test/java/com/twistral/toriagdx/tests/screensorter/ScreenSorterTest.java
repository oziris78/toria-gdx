package com.twistral.toriagdx.tests.screensorter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twistral.toriagdx.screens.ScreenSorter;


public class ScreenSorterTest extends Game {

    SpriteBatch batch;
    BitmapFont font;
    ScreenSorter screenSorter;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        screenSorter = new ScreenSorter();
        screenSorter.putScreen(MyScreens.SCREEN_ONE, ScreenOne.class, ScreenSorterTest.class);
        screenSorter.putScreen(MyScreens.SCREEN_TWO, ScreenTwo.class, ScreenSorterTest.class);
        screenSorter.putScreen(MyScreens.SCREEN_THREE, ScreenThree.class, ScreenSorterTest.class, int.class);

        this.setScreen(screenSorter.getScreen(MyScreens.SCREEN_ONE, this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        screenSorter.disposeAll();
        font.dispose();
        batch.dispose();
    }

}
