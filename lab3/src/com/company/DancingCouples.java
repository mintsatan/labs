package com.company;

public class DancingCouples {
    private String name;
    private DancingPeople human1;
    private DancingPeople human2;
    private String place = "on the screen";
    private  int planetCoordinateX = 67;
    private int planetCoordinateY = 27;
    private int planetCoordinateZ = 99;
    private String spaceObject = "Earth";

    void setPlanetCoordinateX(int coordinateX) {
        this.planetCoordinateX = coordinateX;
    }

    void setPlanetCoordinateY(int coordinateY) {
        this.planetCoordinateY = coordinateY;
    }

    void setPlanetCoordinateZ(int coordinateZ) {
        this.planetCoordinateZ = coordinateZ;
    }

    public String checkCoordinates() {
        if (planetCoordinateX == 67 && planetCoordinateY == 27 && planetCoordinateZ == 99) {
            return "Coordinates of " + getName() + " haven't changed. ";
        } else {
            return "New coordinates: " + planetCoordinateX + ", " + planetCoordinateY + ", " + planetCoordinateZ + ". ";
        }
    }

    String getName() {
        return name;
    }

    Human getHuman1() {
        return human1;
    }

    Human getHuman2() {
        return human2;
    }

    DancingCouples (String name, DancingPeople human1, DancingPeople human2) {
        this.name = name;
        this.human1 = human1;
        this.human2 = human2;
    }

    public String appear() {
        return getName() + ": " + human1 + ", " + human2 + " appeared " + place + ". ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        boolean equ = (obj.hashCode() == this.hashCode()) ? true : false;
        return equ;
    }

}
