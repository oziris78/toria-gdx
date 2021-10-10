package com.telek.telekgdx.box2d;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

public final class TBox2D {

    /*  FIELDS  */
    static float PPM;  // must have the default access modifier


    /*  METHODS  */

    /** Initializes all Box2D classes with PPM, this method must be executed before any usage of any Box2D class */
    public static void init(final float _PPM) {
        PPM = _PPM;
    }


    public static void updateSpritePositionWithBody(Body body, Sprite sprite) {
        sprite.setPosition(body.getPosition().x * PPM - sprite.getWidth() / 2, body.getPosition().y * PPM - sprite.getHeight() / 2);
    }

    public static void updateSpriteRotationWithBody(Body body, Sprite sprite) {
        sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
    }

    public static void moveAndRotateSprite(Body body, Sprite sprite) {
        updateSpritePositionWithBody(body, sprite);
        updateSpriteRotationWithBody(body, sprite);
    }




}
