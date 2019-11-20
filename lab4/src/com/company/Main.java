package com.company;

public class Main {

    public static void main(String[] args) {
        Shorties Kozlic = new Shorties("Kozlic", 1, "Earthling");
        Shorties Neznyaika = new Shorties("Neznyaika", 1, "Earthling");
        System.out.print(Kozlic.lookForAJob());
        System.out.println(Neznyaika.lookForAJob());
        System.out.print(Kozlic.findJob(false));
        System.out.print(Neznyaika.findJob(false));
        System.out.println(Kozlic.say("nothing, we can earn on a 'Fun booth'"));
        System.out.print(Neznyaika.came("'Fun booth'", 3));
        System.out.print(Kozlic.came("'Fun booth'", 3));
        Neznyaika.setPlanetCoordinateX(78);
        Kozlic.setPlanetCoordinateX(78);
        Shorties shorty = new Shorties("Yesterday shorty", 1, "Earthling");
        System.out.println(Neznyaika.saw(shorty));
        System.out.print(Kozlic.saw(shorty));
        System.out.print(shorty.standing());
        System.out.print(Shorties.throwBalls());
        System.out.print(shorty.dodgeBalls(false));
        System.out.println(shorty.didSomething("had", false, "a black eye"));
        System.out.print(Kozlic.calculations(true));
        System.out.print(shorty.dodgeBalls(true));
    }
}
