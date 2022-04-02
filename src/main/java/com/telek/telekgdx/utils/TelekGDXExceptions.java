package com.telek.telekgdx.utils;


public class TelekGDXExceptions extends RuntimeException{

    public TelekGDXExceptions(String message) {
        super(message);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Used to get rid of compiler errors, this is not an actual exception.
     */
    public static class ThisLineIsNeverExecutedException extends RuntimeException {
        public ThisLineIsNeverExecutedException() {
            super("An unexpected error has occured");
        }
    }


}
