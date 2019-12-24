package com.company;

public abstract class Institution {
    public boolean isOpen = false;
    private String name;
    private int square;
    private static TypeOfInstitution type;

    Institution(String name, int square) {
        this.name = name;
        this.square = square;
        Foundation foundation = new Foundation();
    }

    static class Foundation {
        protected void finalize() {
            System.out.print(" Foundation is destroyed.");
        };
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

    public String open() {
        isOpen = true;
        return "Fun booth opened.";
    }


    protected void finalize() {
        System.out.println(this.name + " has been closed.");
    }

}
