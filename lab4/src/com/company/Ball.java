package com.company;

public class Ball extends Things {
    public static TypeOfThings type = TypeOfThings.RUBBER;

    public TypeOfThings getType() {
        return type;
    }

    Ball(String name, double size) {
        super(name, size);
    }
}
