package com.twistral.toriagdx.tests.screensorter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.twistral.toriagdx.screens.TScreen;
import com.twistral.toriagdx.screens.TScreenUtils;

import java.util.Objects;

public class ScreenThree implements TScreen {

    private final ScreenSorterTest game;
    int x;

    public ScreenThree(final ScreenSorterTest game, int x){
        this.game = game;
        this.x = x;
        System.out.println("constructor of screen three");
    }


    @Override
    public void configure() {
        System.out.println("config of screen three");
    }


    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            this.game.setScreen(this.game.screenSorter.getScreen(MyScreens.SCREEN_ONE, game));
        }
    }


    @Override
    public void render(float delta) {
        update(delta);
        TScreenUtils.clearScreen(0f, 0f, 0.25f, 1f);

        this.game.batch.begin();
        this.game.font.draw(this.game.batch, "hi from screen THREE press K to change screens", 100f, 100f);
        this.game.batch.end();
    }


    // unused methods
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenThree screenOne = (ScreenThree) o;
        return Objects.equals(game, screenOne.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game);
    }
}
