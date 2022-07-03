package com.twistral.toriagdx.timing;


public class LimitTimer extends AccumulationTimer {


    //////////////
    /*  FIELDS  */
    //////////////

    /** A limit in seconds. */
    private final float limit;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public LimitTimer(boolean hasAlreadyExceeded, final float limit){
        this.limit = limit;
        this.time = hasAlreadyExceeded ? limit : 0f;
    }

    public LimitTimer(final float limit){
        this(true, limit);
    }

    ///////////////
    /*  METHODS  */
    ///////////////

    public boolean hasPassed(){
        if(this.time >= limit){
            this.time = 0f;
            return true;
        }
        return false;
    }

    public float howManySecsLeft(){
        return (limit >= time) ? limit - time : 0f;
    }

    public float howManyMinsLeft(){
        return howManySecsLeft() / 60f;
    }

    public int howManyMillisecsLeft(){
        return (int) (howManySecsLeft() * 1000f);
    }


}

