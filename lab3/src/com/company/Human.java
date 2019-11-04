package com.company;

public class Human implements HumanPlus {
    private String name;

    Human(String str) {
        name = str;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String didSomething(String action, boolean negation, String thing) {
        return getName() + (negation ? " didn't " : " ") + action + " " + thing + ". ";
    }
}
