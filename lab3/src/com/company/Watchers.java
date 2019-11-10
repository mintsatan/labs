package com.company;

public class Watchers extends Human {
    private  int planetCoordinateX = 12;
    private int planetCoordinateY = 76;
    private int planetCoordinateZ = 88;
    private String spaceObject = "Earth";

    Watchers(String str, int number, String type) {
        super(str, number, type);
    }

    @Override
    public String checkCoordinates() {
        if (planetCoordinateX == 12 && planetCoordinateY == 76 && planetCoordinateZ == 88) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
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
