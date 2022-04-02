package com.telek.telekgdx.simple.particles.musical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MusicalNote extends Image {

    ParticleEffect effect;

    public MusicalNote(){
        super(new Texture("particles/musicnote/note.png"));
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/bubleNote.p"), MusicalNoteTest.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
        effect.update(delta);
    }
}
