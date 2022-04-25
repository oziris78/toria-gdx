package com.telek.telekgdx.simple.scene2d;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.badlogic.gdx.utils.viewport.*;
import com.telek.telekgdx.TelekGDX;
import com.telek.telekgdx.screens.*;


public class DragAndDropTest extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;

    @Override
    public void create() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/plain-james/plain-james-ui.json"));

        stage.setDebugAll(true);
        List<String> inventory = new List<>(skin);
        List<String> merchant = new List<>(skin);

        String[] items = new String[10];
        items[0] = "cursed item";
        for (int i = 1; i < 10; i++) {
            items[i] = "test" + i;
        }
        inventory.setItems(items);

        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults();
        table.add("Inventory");
        table.add("Merchant").row();
        table.add(inventory).expand().fill();
        table.add(merchant).expand().fill();

        Gdx.input.setInputProcessor(stage);

        // DRAG AND DROP
        DragAndDrop dnd = new DragAndDrop();
        // optional to set every button type (left and right) draggable
        // dnd.setButton(-1);
        dnd.addSource(new Source(inventory) {
            final Payload payload = new Payload();
            Label lb;
            @Override
            public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                String item = inventory.getSelected();
                payload.setObject(item);
                inventory.getItems().removeIndex(inventory.getSelectedIndex());
                lb = new Label(item, skin);
                payload.setDragActor(lb);
                // you can set what the merchant says (optional)
                payload.setInvalidDragActor(new Label("bruh fuck no lmao", skin));
                payload.setValidDragActor(new Label("of course", skin));
                return payload;
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                // optional, to make it exactly on our pointer
                event.setStageX(event.getStageX() + lb.getWidth() / 2f);
                event.setStageY(event.getStageY() - lb.getHeight() / 2f);
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
                if(target == null)
                    inventory.getItems().add((String) payload.getObject());
            }
        });

        dnd.addTarget(new Target(merchant) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                // we don't want it to be cursed item (for educational purposes)
                return !"cursed item".equals(payload.getObject());
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                merchant.getItems().add((String) payload.getObject());
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        // update
        stage.act(Gdx.graphics.getDeltaTime());
        if(Gdx.input.isKeyJustPressed(Input.Keys.D))
            this.stage.setDebugAll(!this.stage.isDebugAll());

        // render
        TScreenUtils.clearScreen(0.5f, 0.5f, 0.5f, 1f);
        stage.draw();
    }


}