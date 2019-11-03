package com.company;

import ru.ifmo.se.pokemon.*;

public class Steenee
        extends Bounsweet {
    public Steenee(String name, int lvl) {
        super(name, lvl);
        setType(Type.GRASS);
        setStats(52.00d, 40.00d, 48.00d, 40.00d, 48.00d, 62.00d);
        setMove(new DazzlingGleam(), new Swagger(), new PlayNice());
    }
}

class PlayNice
        extends StatusMove {
    public PlayNice() {
        super(Type.NORMAL, 0.00d, 0.00d);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        p.setMod(Stat.ATTACK, -1);
    }
}