package com.telek.telekgdx.simple.scene2d.raeleus;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.*;
import com.telek.telekgdx.screens.*;


public class RaeleusExampleMySolution extends ApplicationAdapter {

    ScreenViewport viewport;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
    Table root;
    SpriteBatch batch;
    int i = 1;

    @Override
    public void create() {
        super.create();
        camera = new OrthographicCamera(1024f, 768f);
        viewport = new ScreenViewport(camera);
        viewport.apply(true);
        camera.update();
        batch = new SpriteBatch();

        batch.setProjectionMatrix(camera.combined);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/crispy-ui/clean-crispy-ui.json"));


        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        // whole table is 7 columns

        root.pad(15f);
        root.add(btn()).colspan(1).growX();
        root.add().colspan(4).growX();;
        root.add(btn()).colspan(1).growX();
        root.add(btn()).colspan(1).growX();
        root.row();
        root.add().colspan(7).grow();
        root.row();
        root.add().colspan(6);
        root.add(btn()).colspan(1).growX();

        /* bu daha farklı bir çözüm, en aşağıdaki butonun tek bir table konulduğundan nasıl değiştiğine bak
        root.pad(15f);
        Table table = new Table();
        table.add(btn()).colspan(1).growX();
        table.add().colspan(4).growX();
        table.add(btn()).colspan(1).growX();
        table.add(btn()).colspan(1).growX();
        root.add(table).colspan(7).growX();
        root.row();
        root.add().colspan(7).grow();
        root.row();
        root.add().colspan(6);
        root.add(btn()).colspan(1).growX();
        */

//        root.setDebug(true);

    }




    private void update(float deltaTime) {
        stage.act(deltaTime);
    }




    @Override
    public void render() {
        super.render();
        float deltaTime = Gdx.graphics.getDeltaTime();
        update(deltaTime);

        TScreenUtils.clearScreen();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0,0,0,1);

        stage.draw();
    }


    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
        viewport.update(w, h, true);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }


    public TextButton btn(){
        return new TextButton(String.valueOf(i++), skin);
    }

}
