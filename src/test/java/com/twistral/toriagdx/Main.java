package com.twistral.toriagdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.twistral.toriagdx.tests.timing.*;
import com.twistral.toriagdx.tests.box2d.*;
import com.twistral.toriagdx.tests.screensorter.*;
import com.twistral.toriagdx.tests.timing.TimerTest;


public class Main {


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
        new LwjglApplication(new TimerTest(), config);
    }

}
