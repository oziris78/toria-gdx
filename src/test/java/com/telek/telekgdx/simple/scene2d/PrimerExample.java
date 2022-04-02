package com.telek.telekgdx.simple.scene2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.telek.telekgdx.screens.TScreenUtils;

public class PrimerExample extends ApplicationAdapter {

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

        // start layout
        root.pad(15);
        root.defaults().space(10f);
        root.add().colspan(1);
        root.add(btn()).colspan(3).growX();
        root.add(btn()).colspan(1).growX();

        root.row();

        root.add(btn()).colspan(1).grow();

        Table insideTable = new Table();
        insideTable.defaults().space(3.5f);
        insideTable.add(btn()).colspan(1).growX();
        insideTable.add(btn()).colspan(2).growX();
        insideTable.row();
        insideTable.add(btn()).colspan(2).growX();
        insideTable.add(btn()).colspan(1).growX();
        insideTable.row();
        insideTable.add(btn()).colspan(1).growX();
        insideTable.add(btn()).colspan(1).growX();
        insideTable.add(btn()).colspan(1).growX();
        insideTable.row();
        insideTable.add(btn()).colspan(3).grow();

        root.add(insideTable).colspan(3).grow().pad(10);
        root.add(btn()).colspan(1).grow().pad(10);

        root.row();

        root.add(btn()).colspan(5).growX().pad(10);

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
