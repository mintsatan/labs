package com.company;

public abstract class Human {
    protected String name;
    protected String race;
    protected int quantity;
    private  int planetCoordinateX;
    private int planetCoordinateY;
    private int planetCoordinateZ;
    private String spaceObject;

    Human(String str, int number, String type) {
        name = str;
        quantity = number;
        race = type;
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

    public String lookForAJob() {
        if (quantity > 1) {
            return getName() + " are searching for a job. ";
        }
        else {
            return getName() + " is searching for a job. ";
        }
    }

    public String findJob(boolean job) {
        if (job) {
            return getName() + " found a job. ";
        }
        else {
            return "For " + getName() + " the search was unsuccessful. ";
        }
    }

    public String say(String words) {
        return getName() + "says: " + words + ". ";
    }

    public String came(String place) {
        return getName() + "came to " + place + ". ";
    }

    public abstract String standing();

    String getName() {
        return name;
    }

    void setName(String name) {
            this.name = name;
    }

    public abstract String didSomething(String action, boolean negation, String thing);

    abstract public String checkCoordinates();

    public String saw(String view) {
        return getName() + " saw " + view + ". ";
    }

    @Override
    public String toString() {
        return  "Quantity : " + quantity + " " + getName() + " Race: " + race;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Human other = (Human) obj;
        if (quantity != other.quantity)
            return false;
        boolean equ = (obj.hashCode() == this.hashCode()) ? true : false;
        return equ;
    }
}
