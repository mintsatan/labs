package com.company;

import ru.ifmo.se.pokemon.*;

public class Komala
        extends Pokemon {
    public Komala(String name, int lvl) {
        super(name, lvl);
        setType(Type.NORMAL);
        setStats(115.00d, 65.00d, 65.00d, 75.00d, 95.00d, 65.00d);
        setMove(new RockTomb(), new DoubleTeam(), new Psychic(), new ConfuseRay());
    }
}

class DoubleTeam
        extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0.00d, 0.00d);
    }

    @Override
    protected void applySelfEffects (Pokemon p) {
        p.setMod(Stat.DEFENSE,1);
    }
}

class Psychic
        extends SpecialMove {
    public Psychic() {
        super(Type.PSYCHIC, 90.00d, 100.00d);
    }

    @Override
    protected void applyOppEffects (Pokemon p) {
        if (Math.random() < 0.1) p.setMod(Stat.SPECIAL_DEFENSE,-1);
    }
}

class RockTomb
        extends PhysicalMove {
    public RockTomb() {
        super(Type.ROCK, 60.00d, 95.00d);
    }

    @Override
        protected void applyOppEffects(Pokemon p) {
            p.setMod(Stat.SPEED, -6);
        }
}

class ConfuseRay
        extends StatusMove {
    public ConfuseRay() {
        super(Type.GHOST, 0.00d, 100.00d);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.confuse(p);
    }
}