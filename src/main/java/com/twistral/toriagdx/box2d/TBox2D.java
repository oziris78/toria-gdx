package com.twistral.toriagdx.box2d;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public final class TBox2D {

    /*  FIELDS  */

    static float PPM;


    /*  METHODS  */

    /** Initializes all Box2D classes with PPM, this method must be executed before any usage of any Box2D class */
    public static void init(final float _PPM) {
        PPM = _PPM;
    }


    public static void moveSprite(Body body, Sprite sprite) {
        sprite.setPosition(body.getPosition().x * PPM - sprite.getWidth() / 2, body.getPosition().y * PPM - sprite.getHeight() / 2);
    }

    public static void rotateSprite(Body body, Sprite sprite) {
        sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
    }

    public static void moveAndRotateSprite(Body body, Sprite sprite) {
        moveSprite(body, sprite);
        rotateSprite(body, sprite);
    }


    /**
     * Note: first and the last vector must be the same for Box2D to close the shape.
     * @param arr any array including vertices as vectors
     * @param imgWidth width of image
     * @param imgHeight height of image
     * @return changed and ready vector2 array for {@link BodyFactory}
     */
    public static Vector2[] initArrayForChainShapes(Vector2[] arr, float imgWidth, float imgHeight){
        float hWidth = imgWidth / 2f, hHeight = imgHeight / 2f;

        for(Vector2 vec : arr){
            vec.x = (vec.x - hWidth) / PPM;
            vec.y = (hHeight - vec.y) / PPM;
        }

        return arr;
    }


    /**
     * Note: first and the last two floats (points) must be DIFFERENT for Box2D to work properly.
     * @param arr any array including vertices as floats, x1,y1,x2,y2,...
     * @param imgWidth width of image
     * @param imgHeight height of image
     * @return changed and ready float array for {@link BodyFactory}
     */
    public static float[] initArrayForTriangulation(float[] arr, float imgWidth, float imgHeight){
        float hWidth = imgWidth / 2f, hHeight = imgHeight / 2f;
        int elemCount = arr.length;

        for (int i = 0; i < elemCount; i++) {
            /* it's even <=> it's a width */
            if( i % 2 == 0) arr[i] = (arr[i] - hWidth) / PPM;
            else arr[i] = (hHeight - arr[i]) / PPM;
        }

        return arr;
    }



}
