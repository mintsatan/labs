package com.company;

import ru.ifmo.se.pokemon.*;

class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Komala("Babble", 99));
        b.addFoe(new Bounsweet("Linnett", 99));
        b.addAlly(new Venonat("Wynne", 100));
        b.addFoe(new Steenee("Genevieve", 99));
        b.addAlly(new Venomoth("Sam", 100));
        b.addFoe(new Tsareena("Poppy", 99));
        b.go();
    }
}