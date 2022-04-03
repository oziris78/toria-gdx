package com.telek.telekgdx.simple.skincomposer;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.rafaskoberg.gdx.typinglabel.*;
import com.ray3k.stripe.*;



public class SkinComposerTest implements ApplicationListener {

    // SCENE2D
    Stage stage;
    Table table;


    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new FreeTypeSkin(Gdx.files.internal("skinComposerTest/telekSkin/out/telek-skin.json"));
//        Skin skin = new FreeTypeSkin(Gdx.files.internal("skinComposerTest/telekSkin2/out/telek-skin.json"));
        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.top();

        TypingLabel label1 = new TypingLabel("{SPEED=0.1}testsetsetsetset", skin);
        label1.setAlignment(Align.center);
//        table.add(label1).row();

        TextButton tbtn1 = new TextButton("textbutton1", skin, "default");
        TextButton tbtn2 = new TextButton("textbutton2", skin, "default");
//        table.add(btn1).row();
//        table.add(btn2).row();

        ImageButton btn2 = new ImageButton(skin, "default");
//        table.add(btn2).row();

        Button btn3 = new Button(skin, "default");
//        table.add(btn3).row();

        ImageTextButton btn4 = new ImageTextButton("ALLANI SIJIM", skin, "default");
//        table.add(btn4).row();


        Window winPause = new Window("YUNUS OC", skin);
        winPause.padTop(64); // from font64
        winPause.add(label1).row();
        winPause.setSize(500, 250);
        winPause.setMovable(true);
//        table.addActor(winPause);


        CheckBox btn5 = new CheckBox("btn5", skin, "default");
//        table.add(btn5).row();


        List<String> list = new List(skin);
        list.setItems(new String[] {"selection one", "selection two", "selection three"});
        list.setAlignment(Align.center); // yazıyı ortalama
//        table.add(list).row();

        TextField textField = new TextField("", skin);
        textField.setMessageText("anani sikim");
//        table.add(textField).growX().row();

        TextArea textArea = new TextArea("", skin);
        textArea.setMessageText("textarea");
        textArea.setPrefRows(5);
//        table.add(textArea).growX().row();

        Slider slider = new Slider(0, 100, 10, false, skin);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(((Slider) actor).getValue());
            }
        });
//        table.add(slider).width(450).padTop(100f).padBottom(100f).row();

        ProgressBar progressBar = new ProgressBar(0, 100, 10, false, skin);
        progressBar.setValue(50f);
//        table.add(progressBar).width(450).row();

        Table scrollPaneTable = new Table();
        ScrollPane scrollPane = new ScrollPane(scrollPaneTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setFlickScroll(false);
        int i = 0;
        for (int j = 0; j < 10; j++) {
            scrollPaneTable.add(new Label("test label" + i++, skin));
            if (j % 3 == 0) scrollPaneTable.row();
        }
//        table.add(scrollPane).row();

        SelectBox<String> selectBox = new SelectBox<>(skin);
        Array<String> selectBoxItems = new Array<>();
        for (int j = 0; j < 5; j++)
            selectBoxItems.add("test " + String.valueOf(i--));
        selectBox.setItems(selectBoxItems);
        selectBox.setAlignment(Align.center);
        selectBox.getList().setAlignment(Align.center);
        table.add(selectBox);

    }


    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }


}
