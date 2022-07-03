package com.twistral.toriagdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class TScreenUtils {


    /**  Clears the screen with black color, runs both glClearColor(r,g,b,a) and glClear(mask) methods.  */
    public static void clearScreen(){
        Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    /**
     * Clears the screen with black color, runs both glClearColor(r,g,b,a) and glClear(mask) methods.
     * @param r red value
     * @param g gree value
     * @param b blue value
     * @param a alpha value
     */
    public static void clearScreen(float r, float g, float b, float a){
        Gdx.gl20.glClearColor(r, g, b, a);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }



    public static void resizeAndSetFullscreen(int screenWidth, int screenHeight, boolean willBeFullscreen) {
        Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
        if (willBeFullscreen) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }



    public static void resizeTheWindowIfNeeded(int width, int height, int minWidth, int minHeight) {
        boolean shouldBeResized = false;
        if (width < minWidth) {
            width = minWidth;
            shouldBeResized = true;
        }
        if (height < minHeight) {
            height = minHeight;
            shouldBeResized = true;
        }
        if (shouldBeResized)
            Gdx.graphics.setWindowedMode(width, height);
    }


}
