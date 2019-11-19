package com.company;

public class Music extends Sound {

    Music(String str) {
        super(str);
    }

    @Override
    public String sounds() {
        return getName() + " sounds. ";
    }

    @Override
    public String toString() {
        return "Sound: " + getName();
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
