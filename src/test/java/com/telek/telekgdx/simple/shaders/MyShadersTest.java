package com.telek.telekgdx.simple.shaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyShadersTest extends ApplicationAdapter {

    // CHANGE THIS TO USE A DIFFERENT SHADER FROM SHADERS/MYSHADERS DIRECTORY
    final String shaderName = "invert";


    Viewport viewport;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;

    ShaderProgram shader;

    @Override
    public void create() {
        super.create();
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FillViewport(300*aspectRatio,300, camera);
        viewport.apply();
        camera.position.set(300 / 2, 300 / 2, 0);
        camera.update();

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        sprite.setPosition(camera.viewportWidth/2 - texture.getWidth()/2, camera.viewportHeight/2 -texture.getHeight()/2);

        batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(button==0){
                    Vector2 worldCoords = viewport.unproject(new Vector2(screenX,screenY));

                    float change = MathUtils.randomBoolean() ? 0.1f : -0.1f;
                    sprite.setAlpha(sprite.getColor().a + change );
                    System.out.println("worldX: "+ worldCoords.x +"||| worldY: "+ worldCoords.y);
                    return true;
                }
                if(button==1){
                    camera.zoom = camera.zoom>5 ? 1f : camera.zoom+0.4f;
                    updateCamera();
                }
                return false;
            }
        });

        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal("shaders/myshaders/"+shaderName+".vsh"),Gdx.files.internal("shaders/myshaders/"+shaderName+".fsh"));
        batch.setShader(shader);
        System.out.println( shader.isCompiled() ? "shader compiled yay" : shader.getLog() );

    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        /* for earthquake
        shader.bind();
        shader.setUniformf("u_distort", MathUtils.random(4),MathUtils.random(4),0);
        */
        batch.begin();

        sprite.draw(batch);

        batch.end();
    }


    @Override
    public void resize(int w, int h) {
        super.resize(w, h);
        updateCamera(w,h);
        /* for vignette i think or emboss
        shader.bind();
        shader.setUniformf("u_resolution", w,h );

         */
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        texture.dispose();
    }

    private void updateCamera(int width, int height){
        viewport.update(width, height, false);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
    private void updateCamera(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }


}
