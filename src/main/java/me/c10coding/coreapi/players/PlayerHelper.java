package me.c10coding.coreapi.players;

public class PlayerHelper {

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
