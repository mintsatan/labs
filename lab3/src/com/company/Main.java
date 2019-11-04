package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Sound music = new Music("Music");
        System.out.print(music.sounds());
        DancingCouples couple = new DancingCouples("Dancing couples");
        System.out.print(couple.appear("on the screen"));
        Watchers watcher = new Watchers("Watchers");
        System.out.print(watcher.watchBroadcast(Themes.DANCING));
        watcher.setName("Nobody");
        System.out.print(watcher.didSomething("turn off", true, "the TV"));
        watcher.setName("Everyone");
        System.out.println(watcher.watchBroadcast(Themes.COSMONAUTS));
        watcher.setName("Sleepwalkers");
        System.out.print(watcher.didSomething("deceive in", true, "their expectations"));
        System.out.println("In those days were translating some news about: " + Arrays.asList(Themes.values()).subList(1, 4));
    }

}
