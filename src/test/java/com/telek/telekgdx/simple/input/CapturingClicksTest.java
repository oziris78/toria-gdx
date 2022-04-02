package com.telek.telekgdx.simple.input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CapturingClicksTest extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;

	float circleX = 200;
	float circleY = 100;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render() {

		if (Gdx.input.isTouched()) {
			circleX = Gdx.input.getX();
			circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}

		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			circleY++;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S)){
			circleY--;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			circleX--;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.D)){
			circleX++;
		}

		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(circleX, circleY, 75);
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}

}