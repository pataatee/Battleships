package models.weapon;

public class Missile implements Weapon {

    public Missile() {

    }

    public Effect[] use(int x, int y) {
        Effect[] toReturn = new Effect[1];
        Effect effet = new Effect(x, y, EffectType.HIT);
        toReturn[0] = effet;
        return toReturn;
    }

}
