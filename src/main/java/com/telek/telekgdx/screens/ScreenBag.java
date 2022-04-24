package com.telek.telekgdx.screens;

import java.lang.reflect.Constructor;


class ScreenBag<T extends TScreen>{

    T screen;
    Class<T> screenClass;
    Constructor<T> constructor;

    public ScreenBag(Class<T> screenClass, Constructor<T> constructor){
        this.screen = null;
        this.screenClass = screenClass;
        this.constructor = constructor;
    }

}