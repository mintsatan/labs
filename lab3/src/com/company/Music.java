package com.company;

public class Music extends Sound {

    Music(String str) {
        super(str);
    }

    @Override
    public String sounds() {
        return getName() + " sounds. ";
    }
}
