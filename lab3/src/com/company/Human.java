package com.company;

import java.util.Arrays;

public class Human implements HumanPlus, Policeman, inZeroGravity {
    private String name;
    private  int planetCoordinateX = 54;  int planetCoordinateY = 28; int planetCoordinateZ = 33;
    private String spaceObject = "Moon";

    Human(String str) {
        name = str;
    }

    String getName() {
        return name;
    }

    void setPlanetCoordinateX(int coordinateX) {
        this.planetCoordinateX = coordinateX;
    }

    void setPlanetCoordinateY(int coordinateY) {
        this.planetCoordinateY = coordinateY;
    }

    void setPlanetCoordinateZ(int coordinateZ) {
        this.planetCoordinateZ = coordinateZ;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }

    @Override
    public String shoot(String weapon) {
        return getName() + " shot from " + weapon + ". ";
    }

    @Override
    public String getIntoZG() {
        return getName() + " got into zero gravity " + "on the " + spaceObject + " in coordinates: "
                + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
    }

    @Override
    public String reactivePowerPlus() {
        return "The speed of reactive power" + " on the " + spaceObject + " increased. ";
    }

    public String checkCoordinates() {
        if (planetCoordinateX == 54 && planetCoordinateY == 28 && planetCoordinateZ == 33) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    @Override
    public String flyAround() {
        return getName() + " flew around the " + spaceObject + ". ";
    }


}
