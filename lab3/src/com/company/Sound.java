package com.company;

public class Sound {
    String name;
    Sound(String name) {
        this.name = name;
    }

    public String beHeard(){
        return name + " " + "was heard" + ".";
    }

    @Override
    public String toString()
    {
        return name;
    }
}
