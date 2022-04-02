package com.telek.telekgdx.simple.particles.musical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Musician extends Image {

    ParticleEffect effect;
    public Musician(){
        super(new Texture("particles/musicnote/musician.png"));
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("particles/musicnote/musician.p"), MusicalNoteTest.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth() + this.getX(), this.getHeight() + this.getY());
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        effect.update(delta);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        effect.setPosition(this.getWidth()+this.getX(),this.getHeight()+this.getY());
    }


}
