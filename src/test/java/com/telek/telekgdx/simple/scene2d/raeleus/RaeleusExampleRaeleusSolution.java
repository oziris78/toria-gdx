package com.telek.telekgdx.simple.scene2d.raeleus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.telek.telekgdx.screens.TScreenUtils;

public class RaeleusExampleRaeleusSolution extends ApplicationAdapter {

    ScreenViewport viewport;
    OrthographicCamera camera;
    Stage stage;
    Skin skin;
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
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/crispy-ui/clean-crispy-ui.json"));

        // layout starts

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.pad(10.0f);

        Table table1 = new Table();

        TextButton textButton = new TextButton(null, skin);
        table1.add(textButton).expandX().align(Align.left);

        textButton = new TextButton(null, skin);
        table1.add(textButton).padRight(10.0f);

        textButton = new TextButton(null, skin);
        table1.add(textButton);
        table.add(table1).growX();

        table.row();
        table.add().expand();

        table.row();
        textButton = new TextButton(null, skin);
        table.add(textButton).align(Align.right);


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


}
