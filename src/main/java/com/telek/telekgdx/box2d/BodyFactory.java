package com.telek.telekgdx.box2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


/**
 * Use this class to create physics (box2d) bodies.
 */
public class BodyFactory {




    /*  --------------------------------------  */
    /*  --------------- BODIES ---------------  */
    /*  --------------------------------------  */



    public static Body createBodyAsBox(World world, BodyType bodyType, boolean isRotationFixed,
                                      int bodyWidthInPixels, int bodyHeightInPixels,
                                      float bodyPosXInPixels, float bodyPosYInPixels,
                                      float density, float friction, float restitution,
                                      float linearDampingInPixels, float angleInDegrees,
                                      boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                      Object userDataForBody, boolean isSensor)
    {
        // BODY DEFINITION => SHAPE => FIXTURE DEFINITION => BODY
        BodyDef bdef = initBdefAsBox(bodyType, bodyWidthInPixels, bodyHeightInPixels, bodyPosXInPixels, bodyPosYInPixels, angleInDegrees);
        PolygonShape shape = getBoxShape(bodyWidthInPixels, bodyHeightInPixels);
        FixtureDef fdef = initFdef(shape, density, friction, restitution, isSensor, useBitsAndGroupIndexes, categoryBits, maskBits, groupIndex);
        Body body = initBody(world, bdef, fdef, linearDampingInPixels, isRotationFixed, userDataForBody);

        // DISPOSE AND RETURN
        shape.dispose();
        return body;
    }



    public static Body createBodyAsCircle(World world, BodyType bodyType, boolean isRotationFixed,
                                         float leftDownPointXInPixels, float leftDownPointYInPixels, float radiusInPixels,
                                         float density, float friction, float restitution,
                                         float linearDampingInPixels, float angleInDegrees,
                                         boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                         Object userDataForBody, boolean isSensor)
    {
        // BODY DEFINITION => SHAPE => FIXTURE DEFINITION => BODY
        BodyDef bdef = initBdefAsCircle(bodyType, leftDownPointXInPixels, leftDownPointYInPixels, radiusInPixels, angleInDegrees);
        CircleShape shape = getCircleShape(radiusInPixels);
        FixtureDef fdef = initFdef(shape, density, friction, restitution, isSensor, useBitsAndGroupIndexes, categoryBits, maskBits, groupIndex);
        Body body = initBody(world, bdef, fdef, linearDampingInPixels, isRotationFixed, userDataForBody);

        // DISPOSE AND RETURN
        shape.dispose();
        return body;
    }



    public static Body createBodyAsChainShape(World world, BodyType bodyType, boolean isRotationFixed,
                                              int bodyWidthInPixels, int bodyHeightInPixels,
                                              float bodyPosXInPixels, float bodyPosYInPixels,
                                              Vector2[] vertices, float density, float friction, float restitution,
                                              float linearDampingInPixels, float angleInDegrees,
                                              boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                              Object userDataForBody, boolean isSensor)
    {
        // BODY DEFINITION => SHAPE => FIXTURE DEFINITION => BODY
        BodyDef bdef = initBdefAsBox(bodyType, bodyWidthInPixels, bodyHeightInPixels, bodyPosXInPixels, bodyPosYInPixels, angleInDegrees);
        ChainShape shape = getChainShape(vertices);
        FixtureDef fdef = initFdef(shape, density, friction, restitution, isSensor, useBitsAndGroupIndexes, categoryBits, maskBits, groupIndex);
        Body body = initBody(world, bdef, fdef, linearDampingInPixels, isRotationFixed, userDataForBody);

        // DISPOSE AND RETURN
        shape.dispose();
        return body;
    }


    public static Body createBodyAsMultipleTriangles(World world, BodyType bodyType, boolean isRotationFixed,
                                       int bodyWidthInPixels, int bodyHeightInPixels,
                                       float bodyPosXInPixels, float bodyPosYInPixels,
                                       float density, float friction, float restitution,
                                       float linearDampingInPixels, float angleInDegrees,
                                       boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                       Object userDataForBody, boolean isSensor)
    {
        //https://gist.github.com/lyze237/a8f641aa9ad1bbdc00dbc6d09c3d4afe
        // aşağısı değiştirilecek

        // BODY DEFINITION => SHAPE => FIXTURE DEFINITION => BODY
        BodyDef bdef = initBdefAsBox(bodyType, bodyWidthInPixels, bodyHeightInPixels, bodyPosXInPixels, bodyPosYInPixels, angleInDegrees);
        PolygonShape shape = getBoxShape(bodyWidthInPixels, bodyHeightInPixels);
        FixtureDef fdef = initFdef(shape, density, friction, restitution, isSensor, useBitsAndGroupIndexes, categoryBits, maskBits, groupIndex);
        Body body = initBody(world, bdef, fdef, linearDampingInPixels, isRotationFixed, userDataForBody);

        // DISPOSE AND RETURN
        shape.dispose();
        return body;
    }




    /*  --------------------------------------  */
    /*  ----------- ACTUAL METHODS -----------  */
    /*  --------------------------------------  */


    private static Body initBody(World world, BodyDef bdef, FixtureDef fdef,
                                 float linearDampingInPixels, boolean isRotationFixed, Object userDataForBody)
    {
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(linearDampingInPixels);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        return body;
    }


    /////////////////
    /*  BODY DEFS  */
    /////////////////

    public static BodyDef initBdefAsBox(BodyType bodyType, int bodyWidthInPixels, int bodyHeightInPixels,
                                        float bodyPosXInPixels, float bodyPosYInPixels, float angleInDegrees)
    {
        final float PPM = TBox2D.PPM;

        BodyDef bdef = new BodyDef();
        bdef.type = bodyType;
        bdef.position.set( // box2d bodies only use their centers as their position so add half of the sizes
                (bodyPosXInPixels + bodyWidthInPixels / 2) / PPM,
                (bodyPosYInPixels + bodyHeightInPixels / 2) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        return bdef;
    }


    public static BodyDef initBdefAsCircle(BodyType bodyType, float leftDownPointXInPixels, float leftDownPointYInPixels,
                                           float radiusInPixels, float angleInDegrees)
    {
        final float PPM = TBox2D.PPM;

        BodyDef bdef = new BodyDef();
        bdef.type = bodyType;
        bdef.position.set(
                (leftDownPointXInPixels + radiusInPixels) / PPM,
                (leftDownPointYInPixels + radiusInPixels) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        return bdef;
    }


    ////////////////////
    /*  FIXTURE DEFS  */
    ////////////////////

    public static FixtureDef initFdef(Shape shape, float density, float friction, float restitution,
                                      boolean isSensor, boolean useBitsAndGroupIndexes,
                                      short categoryBits, short maskBits, short groupIndex)
    {
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = density;
        fdef.friction = friction;
        fdef.restitution = restitution;
        fdef.isSensor = isSensor;
        if (useBitsAndGroupIndexes) {
            fdef.filter.categoryBits = categoryBits;
            fdef.filter.maskBits = maskBits;
            fdef.filter.groupIndex = groupIndex;
        }

        return fdef;
    }


    //////////////
    /*  SHAPES  */
    //////////////

    public static PolygonShape getBoxShape(int bodyWidthInPixels, int bodyHeightInPixels){
        final float PPM = TBox2D.PPM;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidthInPixels / 2f / PPM, bodyHeightInPixels / 2f / PPM);

        return shape;
    }


    public static CircleShape getCircleShape(float radiusInPixels) {
        final float PPM = TBox2D.PPM;

        CircleShape shape = new CircleShape();
        shape.setRadius(radiusInPixels / PPM);

        return shape;
    }


    public static ChainShape getChainShape(Vector2[] vertices){
        ChainShape shape = new ChainShape();
        shape.createChain(vertices);

        return shape;
    }



}
