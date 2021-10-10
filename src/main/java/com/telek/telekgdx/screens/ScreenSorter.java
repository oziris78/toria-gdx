package com.telek.telekgdx.screens;


import com.badlogic.gdx.Game;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;



/**
 * This class helps you organize your screens in an optimized way especially if you're going to
 * have multiple screens of a single class that extends Screen. <br> <br>
 *
 * This class only accepts screens that implement {@link TScreen}. The {@link TScreen} class
 * defines two additional methods: update(dt), configure(). <br> <br>
 *
 * You're supposted to reset every single field, input etc. you have in the screen in the configure()
 * method. This means you need to write more code but it will pay off at the end. <br> <br>
 *
 * You can also use update(dt) as your update method in each screen, this is a very common method
 * to have in every screen so {@link TScreen} automaticly defines this method for you! <br> <br>
 *
 * @param <TGAME> a type that extends the {@link Game}
 */
public class ScreenSorter<TGAME extends Game> {

    private class TTScreen<TYPESCREEN> {
        private final Class<TYPESCREEN> type;
        private final TScreen screen;

        private TTScreen(Class<TYPESCREEN> type, TScreen screen) {
            this.type = type; this.screen = screen;
        }

        private TScreen getScreen() { return screen; }
        private Class<TYPESCREEN> getType() { return type; }
    }


    private HashMap<String, TTScreen> screens;
    private final TGAME game;


    /*  CONSTRUCTORS  */

    public ScreenSorter(TGAME game) {
        this.game = game;
        this.screens = new HashMap<>();
    }


    /*  METHODS  */

    /**
     * Puts an existing screen to the screensorter
     * @param key any string
     * @param screen an existing screen
     * @param classOfScreen class of this screen
     * @param <T> any class that extends {@link TScreen}
     */
    public <T extends TScreen> void putScreen(String key, TScreen screen, Class<T> classOfScreen) {
        this.screens.put(key, new TTScreen(classOfScreen, screen));
    }



    /**
     * Puts a null screen with the specified class to be created once and returned using the screensorter
     * @param key any string
     * @param classOfScreen class of this screen
     * @param <T> any class that extends {@link TScreen}
     */
    public <T extends TScreen> void putScreen(String key, Class<T> classOfScreen) {
        putScreen(key, null, classOfScreen);
    }



    /**
     * Gets the screen with the specified key from the screensorter, creates a new screen if the current existing one is null.
     * It also runs the tScreen.configure() before returning it (allowing you to reset fields, inputs etc. before you get the screen)
     * @param key a key
     * @param <T> any class that extends {@link TScreen}
     * @return The screen with the specified key from the screensorter
     */
    public <T extends TScreen> T getScreen(String key) {
        T screen = (T) this.screens.get(key).getScreen();
        if (screen == null) {
            try {
                screen = (T) this.screens.get(key).getType().getConstructor(game.getClass()).newInstance(this.game);
            }
            catch (InstantiationException e) { e.printStackTrace(); }
            catch (InvocationTargetException e) { e.printStackTrace(); }
            catch (NoSuchMethodException e) { e.printStackTrace(); }
            catch (IllegalAccessException e) { e.printStackTrace(); }
        }
        screen.configure();
        return screen;
    }



    /**
     * Disposes the screen found by the specified key
     * @param key a key
     */
    public void disposeScreen(String key) {
        if(disposeTheGivenScreen(this.screens.get(key).getScreen()))
            this.screens.remove(key);
    }


    /** Disposes all of the screens that are inside the screensorter */
    public void dispose() {
        for (TTScreen screen : this.screens.values()) disposeTheGivenScreen(screen.getScreen());
    }



    /*  HELPERS  */

    private boolean disposeTheGivenScreen(TScreen screen){
        if(screen != null) {
            screen.dispose();
            return true;
        }
        else return false;
    }


}