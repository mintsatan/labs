package com.company;

import ru.ifmo.se.pokemon.*;

public class Bounsweet
        extends Pokemon {
    public Bounsweet(String name, int lvl) {
        super(name, lvl);
        setType(Type.GRASS);
        setStats(42.00d, 30.00d, 38.00d, 30.00d, 38.00d, 32.00d);
        setMove(new DazzlingGleam(), new Swagger());
    }
}

class Swagger
        extends StatusMove {
    public Swagger() {
        super(Type.NORMAL, 0.00d, 85.00d);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.confuse(p);
        p.setMod(Stat.ATTACK, 2);
    }
}

class DazzlingGleam
        extends SpecialMove {
    public DazzlingGleam() {
        super(Type.FAIRY, 80.00d, 100.00d);
    }
}