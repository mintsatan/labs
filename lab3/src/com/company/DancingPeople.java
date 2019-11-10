package com.company;

public class DancingPeople extends Human {
    private  int planetCoordinateX = 67;
    private int planetCoordinateY = 27;
    private int planetCoordinateZ = 99;
    private String spaceObject = "Earth";

    DancingPeople(String str, int number, String type) {
        super(str, number, type);
    }

    @Override
    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }

    @Override
    public String checkCoordinates() {
        if (planetCoordinateX == 67 && planetCoordinateY == 27 && planetCoordinateZ == 99) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }
}
