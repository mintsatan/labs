package com.company;

public class Main {

    public static void main(String[] args) {
        Sound music = new Music("Music");
        System.out.print(music.sounds());
        DancingCouples couple = new DancingCouples("Dancing couples", new DancingPeople("men", 22, "Earthling"), new DancingPeople("women", 22, "Earthling"));
        System.out.print(couple.appear());
        Watchers watcher = new Watchers("Watchers",  101, "Earthling");
        System.out.println(watcher.watchBroadcast(Themes.DANCING));
        watcher.setName("Nobody");
        System.out.print(watcher.didSomething("turn off", true, "the TV"));
        watcher.setName("Everyone");
        System.out.print(watcher.watchBroadcast(Themes.COSMONAUTS));
        watcher.setName("Sleepwalkers");
        System.out.println(watcher.didSomething("deceive in", true, "their expectations"));
        Radio radio = new Radio("radio", 600, 900);
        System.out.println(radio.translate(Themes.COSMONAUTS, Themes.GIANT_PLANTS, Themes.ZERO_GRAVITY));
        Television television = new Television("television", 600, 900);
        System.out.print(television.translate(Themes.COSMONAUTS, Themes.GIANT_PLANTS, Themes.ZERO_GRAVITY));
        watcher.setName("Everyone");
        System.out.println(watcher.didSomething("struck by", false, "the story of Hnugl"));
        PolicemanInZeroGravity Hnugl = new PolicemanInZeroGravity("Hnugl", 1, "Earthling");
        System.out.print(Hnugl.GetIntoZG());
        System.out.println(Hnugl.shoot("a long-range large-caliber rifle"));
        System.out.print(Hnugl.reactivePowerPlus());
        System.out.print(Hnugl.flyAround());
        System.out.print(Hnugl.checkCoordinates());
        Shorties Kozlic = new Shorties("Kozlic", 1, "Earthling");
        Shorties Neznyaika = new Shorties("Neznyaika", 1, "Earthling");
        System.out.println();
        System.out.println(Kozlic.lookForAJob());
        System.out.println(Neznyaika.lookForAJob());
        System.out.print(Kozlic.findJob(false));
        System.out.print(Neznyaika.findJob(false));
        System.out.println(Kozlic.say("nothing, we can earn on a 'Fun booth'"));
        System.out.print(Neznyaika.came("'Fun booth'"));
        System.out.print(Kozlic.came("'Fun booth'"));
        Neznyaika.setPlanetCoordinateX(78);
        Kozlic.setPlanetCoordinateX(78);
        Shorties shorty = new Shorties("yesterday shorty", 1, "Earthling");
        System.out.println(Neznyaika.saw(shorty));
        System.out.print(Kozlic.saw(shorty));
        System.out.print(shorty.standing());
        System.out.print(Shorties.throwBalls());
        System.out.print(shorty.dodgeBalls(false));
        System.out.print(shorty.didSomething("had", false, "a black eye"));
        
    }
}
