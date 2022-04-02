package com.telek.telekgdx.tests.screensorter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.telek.telekgdx.screens.TScreen;
import com.telek.telekgdx.screens.TScreenUtils;

import java.util.Objects;

public class ScreenOne implements TScreen {

    private final ScreenSorterGame game;

    public ScreenOne(final ScreenSorterGame game){
        this.game = game;
        System.out.println("constructor of screen one");
    }


    @Override
    public void configure() {
        System.out.println("config of screen one");
    }


    @Override
    public void update(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            this.game.setScreen(this.game.screenSorter.getScreen("screenTwo"));
        }
    }


    @Override
    public void render(float delta) {
        update(delta);
        TScreenUtils.clearScreen(0.1f, 0.1f, 0.1f, 1f);

        this.game.batch.begin();
        this.game.font.draw(this.game.batch, "hi from screen one press K to change screens", 100f, 100f);
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
        ScreenOne screenOne = (ScreenOne) o;
        return Objects.equals(game, screenOne.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game);
    }
}
