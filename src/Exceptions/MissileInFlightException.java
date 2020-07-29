package Exceptions;

import sun.awt.CausedFocusEvent;

public class MissileInFlightException extends Exception{

    public MissileInFlightException(String message){
        super(message);
    }

}
