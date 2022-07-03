package com.twistral.toriagdx.timing;


public class AccumulationTimer {

    //////////////
    /*  FIELDS  */
    //////////////


    /** Elapsed time in seconds. */
    protected float time;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public AccumulationTimer(float initialTime){
        this.time = initialTime;
    }

    public AccumulationTimer(){
        this(0f);
    }


    ///////////////
    /*  METHODS  */
    ///////////////

    public void update(float deltaTime){
        this.time += deltaTime;
    }

    public float getTime() {
        return time;
    }



    //////////////////////////////////////////////////////
    ////////////////   STRING METHODS   //////////////////
    //////////////////////////////////////////////////////


    public String toDayString(){
        return TimingUtils.toDayString(time);
    }

    public String toHourString(){
        return TimingUtils.toHourString(time);
    }

    public String toMinuteString(){
        return TimingUtils.toMinuteString(time);
    }

    public String toSecondString(){
        return TimingUtils.toSecondString(time);
    }

    public String toDayStringNoMilli(){
        return TimingUtils.toDayStringNoMilli(time);
    }

    public String toHourStringNoMilli(){
        return TimingUtils.toHourStringNoMilli(time);
    }

    public String toMinuteStringNoMilli(){
        return TimingUtils.toMinuteStringNoMilli(time);
    }

    public String toSecondStringNoMilli(){
        return TimingUtils.toSecondStringNoMilli(time);
    }





}
