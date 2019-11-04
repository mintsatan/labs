package com.company;

public class Watchers extends Human {

    Watchers(String str) {
        super(str);
    }

    public String watchBroadcast(Themes broadcast) {
        switch (broadcast) {
            case DANCING:
                return getName() + " not interested in " + broadcast + ". ";
            case COSMONAUTS:
                return getName() + " waiting for " + "broadcast about " + broadcast + ". ";
            default:
                return "";
        }
    }
}
