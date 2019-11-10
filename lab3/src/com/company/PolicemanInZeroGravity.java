package com.company;

public class PolicemanInZeroGravity extends Human implements Police, inZeroGravity{
    private  int planetCoordinateX = 54;
    private int planetCoordinateY = 28;
    private int planetCoordinateZ = 33;
    private String spaceObject = "Moon";

    PolicemanInZeroGravity(String str, int number, String type) {
        super(str, number, type);
    }

    @Override
    public String checkCoordinates() {
        if (planetCoordinateX == 54 && planetCoordinateY == 28 && planetCoordinateZ == 33) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    @Override
    public String shoot(String weapon) {
        return getName() + " shot from " + weapon + ". ";
    }

    @Override
    public String GetIntoZG() {
        return getName() + " got into zero gravity " + "on the " + spaceObject + " in coordinates: "
                + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
    }

    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }

    @Override
    public String flyAround() {
        return getName() + " flew around the " + spaceObject + ". ";
    }

    @Override
    public String reactivePowerPlus() {
        return "The speed of reactive power" + " on the " + spaceObject + " increased. ";
    }

}
