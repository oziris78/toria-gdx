package com.telek.telekgdx.playground.walkers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Walker2 extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;

	float originX = 200;
	float originY = 100;
	float width = 20;
	float height = 20;
	int movePixel = 100;


	@Override
	public void render () {
		/*
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		 */
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		int valX = MathUtils.randomBoolean() ? MathUtils.random(0,movePixel) : MathUtils.random(-movePixel,0);
		int valY = MathUtils.randomBoolean() ? MathUtils.random(0,movePixel) : MathUtils.random(-movePixel,0);
		Color color = MathUtils.randomBoolean() ? Color.GRAY : Color.DARK_GRAY;
		originX += valX * Gdx.graphics.getDeltaTime();
		originY += valY * Gdx.graphics.getDeltaTime();
		shapeRenderer.setColor(color);
		shapeRenderer.rect(originX,originY,width,height);


		shapeRenderer.end();
	}



	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
	}


	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}



}