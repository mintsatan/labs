package com.company;

public abstract class Human implements Pain {
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
            return getName() + " is searching for a job. ";
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
        return getName() + " says: " + words + ". ";
    }

    public String came(TypeOfInstitution somewhere, int speed) {
        if (speed <= 10) {
            return getName() + " came to " + somewhere + ". ";
        } else if (speed == 0) {
            return getName() + standing() + ". ";
        } else {
            return getName() + " run to " + somewhere + ". ";
        }
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

    public String toRegret(Human someone) {
        return getName() + " regrets to " + someone.getName() + ". ";
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
