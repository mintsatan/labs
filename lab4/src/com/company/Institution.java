package com.company;

public abstract class Institution {
    private String name;
    private int square;
    private static TypeOfInstitution type;

    Institution(String name, int square, TypeOfInstitution type) {
        this.name = name;
        this.square = square;
        this.type = type;
    }

    public int getSquare() {
        return square;
    }

    public String getName() {
        return name;
    }

    public TypeOfInstitution getType() {
        return type;
    }

    protected static void setType(TypeOfInstitution institution) {
        type = institution;
    }

    public abstract String open();

    protected void finalize() {
        System.out.println(this.name + " has been closed.");
    }

}
