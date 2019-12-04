package com.company;

public abstract class Things implements Quality, Fall{
    protected String name;
    protected double size;
    protected static TypeOfThings type;
    protected int strength = Quality.quality(getType());

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public TypeOfThings getType() {
        return type;
    }

    public int getStrength() {
        return strength;
    }

    protected void setStrength(int qual) {
        this.strength = qual;
    }

    @Override
    public String fall() {
        if (getStrength() != 0) {
            setStrength(getStrength() - 1); }
        else finalize();
        return getName() + " fell.";
    }

    Things(String name, double size) {
        this.name = name;
        this.size = size;
    }

    protected void finalize() {
        System.out.println(this.name + " broke.");
    }
}
