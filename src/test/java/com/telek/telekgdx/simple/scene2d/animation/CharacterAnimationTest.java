package com.telek.telekgdx.simple.scene2d.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class CharacterAnimationTest extends ApplicationAdapter {

    public static final float WORLD_WIDTH = 300;
    public static final float WORLD_HEIGHT = 300;

    OrthographicCamera camera;
    Stage stage;
    Player player;
    Image tree, tree2, bg;

    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create() {
        super.create();
        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        player = new Player();
        bg = new Image(new Texture(Gdx.files.internal("images/playerRunning/bgPre.png")));
        tree = new Image(new Texture(Gdx.files.internal("images/playerRunning/bgPost.png")));
        tree2 = new Image(new Texture(Gdx.files.internal("images/playerRunning/bgPost2.png")));

        stage.addActor(bg);
        stage.addActor(tree2);
        stage.addActor(player);
        stage.addActor(tree);

        stage.setKeyboardFocus(player);

        font = new BitmapFont(Gdx.files.internal("font/myFont/myFont2.fnt"), Gdx.files.internal("font/myFont/myFont2.png"), false);
        font.getData().setScale(0.25f);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();


        stage.act(Gdx.graphics.getDeltaTime());


        stage.draw();

        batch.begin();
        font.draw(batch, "test", stage.getWidth() / 2 - 50, stage.getHeight() / 2,100, Align.center, false);
        batch.end();


    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
