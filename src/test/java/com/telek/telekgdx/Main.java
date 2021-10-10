package com.telek.telekgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.telek.telekgdx.tests.TextureTest;

public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;  config.height = 600;
        new LwjglApplication(new TextureTest(), config);
    }

}
