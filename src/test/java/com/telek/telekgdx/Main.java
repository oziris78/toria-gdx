package com.telek.telekgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.telek.telekgdx.tests.timing.*;
import com.telek.telekgdx.tests.box2d.*;
import com.telek.telekgdx.tests.screensorter.*;


public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
        new LwjglApplication(new TimerTest(), config);
    }

}
