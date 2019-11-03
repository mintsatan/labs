package com.company;

import ru.ifmo.se.pokemon.*;

public class Venomoth
        extends Venonat {
    public Venomoth(String name, int lvl) {
        super(name, lvl);
        setType(Type.BUG, Type.POISON);
        setStats(70.00d, 65.00d, 60.00d, 90.00d, 75.00d, 90.00d);
        setMove(new Confide(), new Rest(), new LeechLife(), new Roost());
    }
}

class Roost
        extends StatusMove {
    public Roost() {
        super(Type.FLYING, 0.00d, 0.00d);
    }

    @Override
    protected void applySelfEffects (Pokemon p) {
        if (p.getHP() < 35.00) p.setMod(Stat.HP, 35);
    }
}