package com.company;

import ru.ifmo.se.pokemon.*;

public class Venonat
        extends Pokemon {
     public Venonat(String name, int lvl) {
          super(name, lvl);
          setType(Type.BUG, Type.POISON);
          setStats(60.00d, 55.00d, 50.00d, 40.00d, 55.00d, 45.00d);
          setMove(new Confide(), new Rest(), new LeechLife());
     }
}

class LeechLife
        extends PhysicalMove {
     public LeechLife() {
          super(Type.BUG, 80.00d, 100.00d);
     }

     @Override
     protected void applySelfEffects (Pokemon p) {
          p.setMod(Stat.HP, (int) (p.getHP() + ((60 - p.getHP()) / 2)));
     }
}

class Rest
        extends StatusMove {
     public Rest() {
          super(Type.PSYCHIC, 0.00d, 0.00d);
     }

     @Override
     protected void applySelfEffects(Pokemon p) {
          Effect e = new Effect().turns(2);
          e.sleep(p);
          p.setMod(Stat.HP, 60);
     }
}

class Confide
        extends StatusMove {
     public Confide() {
          super(Type.NORMAL, 0.00d, 0.00d);
     }

     @Override
     protected void applyOppEffects(Pokemon p) {
          p.setMod(Stat.SPECIAL_ATTACK, -1);
     }
}