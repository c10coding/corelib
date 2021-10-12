package net.dohaw.corelib;

public class NoHookException extends Exception{

    public NoHookException(){
        super("You are probably attempting to get the CoreLib instance. You can't do this if you aren't hooked into CoreLib via CoreLib#setInstance()");
    }

}
