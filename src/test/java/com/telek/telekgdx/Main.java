package com.telek.telekgdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.telek.telekgdx.simple.scene2d.DragAndDropTest;


public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
        new LwjglApplication(new DragAndDropTest(), config);
    }

}
