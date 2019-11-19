package com.company;

public abstract class Sound {
    private String name;

    Sound(String str) {
        name = str;
    }

    public abstract String sounds();

    String getName() {
        return name;
    }
}
