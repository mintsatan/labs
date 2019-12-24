package com.company;

public class Main {

    public static void main(String[] args) throws IsTheObjectExistException {
        FunBooth funBooth = new FunBooth("'Fun Booth'", 4);
        funBooth.open();
        FunBooth.Curtain curtain = funBooth.new Curtain();
        Shorties Kozlic = new Shorties("Kozlic", 1, "Earthling", new ShortysHouse("Kozlic's house", 6));
        Shorties Neznyaika = new Shorties("Neznyaika", 1, "Earthling", new ShortysHouse("Neznyaika's house", 6));
        System.out.print(Kozlic.lookForAJob());
        System.out.println(Neznyaika.lookForAJob());
        System.out.print(Kozlic.findJob(false));
        System.out.print(Neznyaika.findJob(false));
        System.out.println(Kozlic.say("nothing, we can earn on a 'Fun booth'"));
        try {
            System.out.print(Neznyaika.came(funBooth, 3));
            System.out.print(Kozlic.came(funBooth, 3));
        } catch (InstitutionIsOpenException err) {
            System.out.print(err.getMessage());
            System.exit(0);
        }
        Neznyaika.setPlanetCoordinateX(78);
        Kozlic.setPlanetCoordinateX(78);
        Shorties shorty = new Shorties("Yesterday shorty", 1, "Earthling", new ShortysHouse("Yesterday shorty's house", 5)) {

            @Override
            public String lookForAJob() {
                if (funBooth.isOpen) {
                    return "  Yesterday shorty made faces.";
                } else {
                    return " Yesterday shorty restores health";
                }
            }
        };

        try {
            System.out.print(funBooth.earnings(shorty));
        } catch (InstitutionIsOpenException err) {
            System.out.print(err.getMessage());
            System.exit(0);
        }
        System.out.println(Neznyaika.saw(shorty));
        System.out.print(Kozlic.saw(shorty));
        System.out.print(shorty.standing());
        Ball ball1 = new Ball("balls", 4.3);
        System.out.print(Shorties.throwBalls());
        try {
            System.out.print(shorty.dodge(false, ball1));
        } catch (Exception e) {
            if (shorty == null) {
                if (shorty.getClass() != Shorties.class) {
                    throw new IsTheObjectExistException("Object doesn't exist");
                } else {
                    throw new NullPointerException();
                }
            }
        }
        System.out.println(curtain.closeCurtain());
        System.out.print(Kozlic.calculations(true));
        System.out.println(shorty.dodge(true, ball1));
        shorty.setPlanetCoordinateY(54);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(Kozlic.toRegret(shorty));
        System.out.print(Kozlic.findJob(true));
        try {
            System.out.print(funBooth.earnings(Kozlic));
            System.out.print(funBooth.earnings(Neznyaika));
        } catch (InstitutionIsOpenException err) {
            System.out.print(err.getMessage());
            System.exit(0);
        }
    }
}
