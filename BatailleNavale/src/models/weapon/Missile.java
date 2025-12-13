package models.weapon;

public class Missile extends Weapon {

    public Missile() {
        super(WeaponType.MISSILE);

    }

    public Effect[] use(int x, int y) {
        Effect[] toReturn = new Effect[1];
        Effect effet = new Effect(x, y, EffectType.HIT);
        toReturn[0] = effet;
        return toReturn;
    }

}
