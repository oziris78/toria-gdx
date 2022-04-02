package com.telek.telekgdx.simple.scene2d.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

class Player extends Image {

    Animation<TextureRegion> runningLeft, runningRight, runningUp, runningDown;
    private float stateTimeLeft = 0;
    private float stateTimeUp = 0;
    private float stateTimeDown = 0;
    private float stateTimeRight = 0;

    private static final int FRAME_COLS = 9 ;
    private static final int FRAME_ROWS = 1;
    private static final int SPEED = 2;

    private int verticalDirection = 0; // DOWN -1, STILL 0, UP 1
    private int horizontalDirection = 0; // LEFT -1, STILL 0, RIGHT 1

    private boolean isClicked ;

    TextureRegion standingTextureRegion;
    private TextureRegion currentFrame = null;


    public Player(){
        super();
        setBounds(50, 50, 64, 64);

        runningLeft = createAnimation("images/playerRunning/solaKosma.png");
        runningRight = createAnimation("images/playerRunning/sagaKosma.png");
        runningUp = createAnimation("images/playerRunning/yukariKosma.png");
        runningDown = createAnimation("images/playerRunning/asagiKosma.png");

        standingTextureRegion = new TextureRegion( new Texture( Gdx.files.internal("images/playerRunning/asagiKosma.png") ) ,0,0,64,64);

        setTouchable(Touchable.enabled);




    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        currentFrame = getCurrentFrame();
        if(currentFrame != null) {
            batch.draw(currentFrame, getX(), getY());
        }
        else{
            batch.draw(standingTextureRegion, getX(), getY());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTimeDown += delta; stateTimeLeft += delta; stateTimeRight += delta; stateTimeUp += delta;
        handleInput();
        movePlayer();
    }

    private void handleInput() {
        isClicked = false;
        resetDirections();
        if(Gdx.input.isKeyPressed(Input.Keys.W) ){
            verticalDirection = 1;
            isClicked = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) ){
            horizontalDirection = -1;
            isClicked = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) ){
            horizontalDirection = 1;
            isClicked = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) ){
            verticalDirection = -1;
            isClicked = true;
        }
        if(!isClicked){
            resetDirections();
        }

    }

    private void movePlayer() {
        if(verticalDirection == 1)    {
            if(getY()+getHeight() + SPEED -13 < CharacterAnimationTest.WORLD_HEIGHT)
                this.addAction( Actions.moveBy(0, SPEED));
        }
        if(verticalDirection == -1)  {
            if(getY()-SPEED >= 0)
                this.addAction( Actions.moveBy(0, -SPEED));
        }
        if(horizontalDirection == -1)  {
            if( getX()+ -SPEED + 8 >= 0)
            this.addAction( Actions.moveBy(-SPEED, 0));
        }
        if(horizontalDirection == 1) {
            if(getX()+getWidth() + SPEED - 14 < CharacterAnimationTest.WORLD_WIDTH)
            this.addAction( Actions.moveBy(SPEED, 0));
        }
    }

    private TextureRegion getCurrentFrame(){
        if(verticalDirection == -1)  return runningDown.getKeyFrame(stateTimeDown, true);
        if(horizontalDirection == -1)  return runningLeft.getKeyFrame(stateTimeLeft, true);
        if(horizontalDirection == 1) return runningRight.getKeyFrame(stateTimeRight, true);
        if(verticalDirection == 1)    return runningUp.getKeyFrame(stateTimeUp, true);
        return null;
    }

    private Animation<TextureRegion> createAnimation(String internalPathToSpriteSheet){
        Texture walkSheet = new Texture(Gdx.files.internal(internalPathToSpriteSheet));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(0.15f, walkFrames);
    }


    private void resetDirections(){
        verticalDirection = 0;
        horizontalDirection = 0;
    }

}
