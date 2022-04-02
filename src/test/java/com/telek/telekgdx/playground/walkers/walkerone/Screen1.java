package com.telek.telekgdx.playground.walkers.walkerone;

import com.badlogic.gdx.*;
import com.telek.telekgdx.screens.TScreenUtils;


class Screen1 extends ScreenAdapter implements Screen {

    Walker1 game;

    Screen1(Walker1 game){
        this.game = game;
    }

    public void update(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.D))
            game.setScreen(new Screen2(game));
    }

    @Override
    public void render(float delta) {
        update(delta);
        TScreenUtils.clearScreen();

        game.batch.begin();
        game.font.setColor(1f,0,0,1f);
        game.font.draw(game.batch, "Click D to start", 200, 200);
        game.batch.end();
        super.render(delta);
    }


}
