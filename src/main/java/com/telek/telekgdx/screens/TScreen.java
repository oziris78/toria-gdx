package com.telek.telekgdx.screens;

import com.badlogic.gdx.Screen;

public interface TScreen extends Screen {

    void update(float delta);

    /** Reset every field you have in this method. */
    void configure();

}
