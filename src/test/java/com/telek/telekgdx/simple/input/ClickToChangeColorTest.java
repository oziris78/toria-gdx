package com.telek.telekgdx.simple.input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class ClickToChangeColorTest extends ApplicationAdapter implements InputProcessor {
	ShapeRenderer shapeRenderer;

	float r = MathUtils.random();
	float g = MathUtils.random();
	float b = MathUtils.random();

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(r, g, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}



	private boolean ranColor(){
		r = MathUtils.random();
		g = MathUtils.random();
		b = MathUtils.random();
		return true;
	}

	@Override
	public boolean keyTyped (char key)  {
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {return ranColor();}

	@Override
	public boolean keyDown(int keycode){return ranColor();}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}