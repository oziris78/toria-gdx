package com.telek.telekgdx.simple.scene2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.telek.telekgdx.screens.TScreenUtils;

public class DialogTest extends ApplicationAdapter {

    ScreenViewport viewport;
    OrthographicCamera camera;
    Stage stage, dialogStage;
    Skin skin;
    TextButton btn1, btn2, btn3;
    Table root, dialogRoot;
    SpriteBatch batch;

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
        dialogStage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/crispy-ui/clean-crispy-ui.json"));

        dialogRoot = new Table();
        dialogRoot.setFillParent(true);
        dialogStage.addActor(dialogRoot);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        btn1 = new TextButton("- 1 -", skin);
        btn2 = new TextButton("- 2 -", skin);
        btn3 = new TextButton("- 3 -", skin);

        btn2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Dialog dialog = new Dialog("test", skin);
                dialogRoot.add(dialog);

            }
        });


        root.pad(10f);
        root.add(btn1).top();
        root.add().expand();
        root.add(btn2).bottom();

        root.setDebug(true);

    }




    private void update(float deltaTime) {
        dialogStage.act(deltaTime);
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

        dialogStage.draw();
        stage.draw();
    }


    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
        viewport.update(w, h, true);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }


}
