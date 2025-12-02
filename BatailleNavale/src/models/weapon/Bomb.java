package models.weapon;

public class Bomb implements Weapon {

    public Bomb() {

    }

    public Effect[] use(int x, int y) {
        Effect[] toReturn = new Effect[5];

        toReturn[0] = new Effect(x, y, EffectType.HIT);
        toReturn[1] = new Effect(x, y-1, EffectType.HIT);
        toReturn[2] = new Effect(x+1, y, EffectType.HIT);
        toReturn[3] = new Effect(x, y+1, EffectType.HIT);
        toReturn[4] = new Effect(x-1, y, EffectType.HIT);

        return toReturn;
    }

}
