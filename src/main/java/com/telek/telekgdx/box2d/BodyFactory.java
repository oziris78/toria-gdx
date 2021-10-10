package com.telek.telekgdx.box2d;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;


/**
 * Creates bodies that only have one fixture attached to them.
 * Use this class to create simple physics bodies.
 */
public class BodyFactory {


    /*  --------------------------------------  */
    /*  ----------- DYNAMIC BODIES -----------  */
    /*  --------------------------------------  */



    public static Body createDynamicBodyAsBox(World world, boolean isRotationFixed,
                                              int bodyWidthInPixels, int bodyHeightInPixels,
                                              float bodyPosXInPixels, float bodyPosYInPixels,
                                              float density, float friction, float restitution,
                                              float linearDampingInPixels, float angleInDegrees,
                                              boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                              Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set( // box2d bodies only use their centers as their position so add half of the sizes
                (bodyPosXInPixels + bodyWidthInPixels / 2) / PPM,
                (bodyPosYInPixels + bodyHeightInPixels / 2) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidthInPixels / 2 / PPM, bodyHeightInPixels / 2 / PPM);

        // FIXTURE DEFINITION
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

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(linearDampingInPixels);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }



    public static Body createDynamicBodyAsCircle(World world, boolean isRotationFixed,
                                                 float leftDownPointXInPixels, float leftDownPointYInPixels, float radiusInPixels,
                                                 float density, float friction, float restitution,
                                                 float linearDampingInPixels, float angleInDegrees,
                                                 boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                                 Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(
                (leftDownPointXInPixels + radiusInPixels) / PPM,
                (leftDownPointYInPixels + radiusInPixels) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        CircleShape shape = new CircleShape();
        shape.setRadius(radiusInPixels / PPM);

        // FIXTURE DEFINITION
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

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(linearDampingInPixels);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }





    /*  --------------------------------------  */
    /*  ---------- KINEMATIC BODIES ----------  */
    /*  --------------------------------------  */



    public static Body createKinematicBodyAsBox(World world, boolean isRotationFixed,
                                                int bodyWidthInPixels, int bodyHeightInPixels,
                                                float bodyPosXInPixels, float bodyPosYInPixels,
                                                float density, float friction, float restitution,
                                                float linearDampingInPixels, float angleInDegrees,
                                                boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                                Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set( // box2d bodies only use their centers as their position so add half of the sizes
                (bodyPosXInPixels + bodyWidthInPixels / 2) / PPM,
                (bodyPosYInPixels + bodyHeightInPixels / 2) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidthInPixels / 2 / PPM, bodyHeightInPixels / 2 / PPM);

        // FIXTURE DEFINITION
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

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(linearDampingInPixels);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }



    public static Body createKinematicBodyAsCircle(World world, boolean isRotationFixed,
                                                   float leftDownPointXInPixels, float leftDownPointYInPixels, float radiusInPixels,
                                                   float density, float friction, float restitution,
                                                   float linearDampingInPixels, float angleInDegrees,
                                                   boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                                   Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.KinematicBody;
        bdef.position.set(
                (leftDownPointXInPixels + radiusInPixels) / PPM,
                (leftDownPointYInPixels + radiusInPixels) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        CircleShape shape = new CircleShape();
        shape.setRadius(radiusInPixels / PPM);

        // FIXTURE DEFINITION
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

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setLinearDamping(linearDampingInPixels);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }




    /*  --------------------------------------  */
    /*  ----------- STATIC BODIES ------------  */
    /*  --------------------------------------  */



    public static Body createStaticBodyAsBox(World world, boolean isRotationFixed,
                                             int bodyWidthInPixels, int bodyHeightInPixels,
                                             float bodyPosXInPixels, float bodyPosYInPixels,
                                             float angleInDegrees,
                                             boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                             Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set( // box2d bodies only use their centers as their position so add half of the sizes
                (bodyPosXInPixels + bodyWidthInPixels / 2) / PPM,
                (bodyPosYInPixels + bodyHeightInPixels / 2) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(bodyWidthInPixels / 2 / PPM, bodyHeightInPixels / 2 / PPM);

        // FIXTURE DEFINITION
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.5f;
        fdef.friction = 0.5f;
        fdef.restitution = 0.5f;
        fdef.isSensor = isSensor;
        if (useBitsAndGroupIndexes) {
            fdef.filter.categoryBits = categoryBits;
            fdef.filter.maskBits = maskBits;
            fdef.filter.groupIndex = groupIndex;
        }

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }



    public static Body createStaticBodyAsCircle(World world, boolean isRotationFixed,
                                                float leftDownPointXInPixels, float leftDownPointYInPixels, float radiusInPixels,
                                                float angleInDegrees,
                                                boolean useBitsAndGroupIndexes, short categoryBits, short maskBits, short groupIndex,
                                                Object userDataForBody, boolean isSensor)
    {
        final float PPM = TBox2D.PPM;
        // BODY DEFINITION
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(
                (leftDownPointXInPixels + radiusInPixels) / PPM,
                (leftDownPointYInPixels + radiusInPixels) / PPM
        );
        bdef.angle = angleInDegrees * MathUtils.degreesToRadians; // box2d uses radians

        // SHAPE
        CircleShape shape = new CircleShape();
        shape.setRadius(radiusInPixels / PPM);

        // FIXTURE DEFINITION
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.5f;
        fdef.friction = 0.5f;
        fdef.restitution = 0.5f;
        fdef.isSensor = isSensor;
        if (useBitsAndGroupIndexes) {
            fdef.filter.categoryBits = categoryBits;
            fdef.filter.maskBits = maskBits;
            fdef.filter.groupIndex = groupIndex;
        }

        // BODY
        Body body = world.createBody(bdef);
        body.createFixture(fdef);
        body.setFixedRotation(isRotationFixed);
        body.setUserData(userDataForBody);

        shape.dispose();
        return body;
    }


}
