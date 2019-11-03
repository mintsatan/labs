package com.company;

import ru.ifmo.se.pokemon.*;

public class Tsareena
        extends Steenee {
    public Tsareena(String name, int lvl) {
        super(name, lvl);
        setType(Type.GRASS);
        setStats(72.00d, 120.00d, 98.00d, 50.00d, 98.00d, 72.00d);
        setMove(new DazzlingGleam(), new Swagger(), new PlayNice(), new LowSweep());
    }
}

class LowSweep
        extends PhysicalMove {
    LowSweep() {
        super(Type.FIGHTING, 65.00d, 100.00d);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        p.setMod(Stat.SPEED, -1);
    }
}

