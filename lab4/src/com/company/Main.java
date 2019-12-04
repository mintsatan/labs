package com.company;

public class Main {

    public static void main(String[] args) {
        FunBooth funBooth = new FunBooth("'Fun Booth'", 4, TypeOfInstitution.BOOTH);
        System.out.println(funBooth.open());
        Shorties Kozlic = new Shorties("Kozlic", 1, "Earthling");
        Shorties Neznyaika = new Shorties("Neznyaika", 1, "Earthling");
        System.out.print(Kozlic.lookForAJob());
        System.out.println(Neznyaika.lookForAJob());
        System.out.print(Kozlic.findJob(false));
        System.out.print(Neznyaika.findJob(false));
        System.out.println(Kozlic.say("nothing, we can earn on a 'Fun booth'"));
        System.out.print(Neznyaika.came(TypeOfInstitution.BOOTH, 3));
        System.out.print(Kozlic.came(TypeOfInstitution.BOOTH, 3));
        Neznyaika.setPlanetCoordinateX(78);
        Kozlic.setPlanetCoordinateX(78);
        Shorties shorty = new Shorties("Yesterday shorty", 1, "Earthling");
        System.out.print(funBooth.earnings(shorty));
        System.out.println(Neznyaika.saw(shorty));
        System.out.print(Kozlic.saw(shorty));
        System.out.print(shorty.standing());
        Ball ball1 = new Ball("balls", 4.3);
        System.out.print(Shorties.throwBalls());
        System.out.print(shorty.dodge(false, ball1));
        System.out.println(shorty.didSomething("had", false, "a black eye"));
        System.out.print(Kozlic.calculations(true));
        System.out.println(shorty.dodge(true, ball1));
        shorty.setPlanetCoordinateY(54);
        System.out.print(Kozlic.toRegret(shorty));
        System.out.print(Kozlic.findJob(true));
        System.out.print(funBooth.earnings(Kozlic));
        System.out.print(funBooth.earnings(Neznyaika));
    }
}
