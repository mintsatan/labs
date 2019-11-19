package com.company;

public class Shorties extends Human {
    private  int planetCoordinateX = 56;
    private int planetCoordinateY = 98;
    private int planetCoordinateZ = 3;

    Shorties(String str, int number, String type) {
        super(str, number, type);
    }

    final static String throwBalls() {
        return "someone trows Balls. ";
    }

    final String dodgeBalls(boolean hit) {
        return getName() + (hit ? "didn't have time to dodge" : "dodges balls");
    }

    @Override
    public String standing() {
        return getName() + " is standing on the platform. ";
    }

    @Override
    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }

    @Override
    public String checkCoordinates() {
        if (planetCoordinateX == 56 && planetCoordinateY == 98 && planetCoordinateZ == 3) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    public String saw(Shorties shorty) {
        return getName() + " saw " + shorty.getName() + ". ";
    }
}

