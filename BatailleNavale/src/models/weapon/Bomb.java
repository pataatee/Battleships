package models.weapon;

public class Bomb extends Weapon {

    public Bomb() {
        super(WeaponType.BOMB);

    }

    public Effect[] use(int x, int y) {
        Effect[] toReturn = new Effect[5];

        toReturn[0] = new Effect(x, y, EffectType.BOMB);
        toReturn[1] = new Effect(x, y-1, EffectType.BOMB);
        toReturn[2] = new Effect(x+1, y, EffectType.BOMB);
        toReturn[3] = new Effect(x, y+1, EffectType.BOMB);
        toReturn[4] = new Effect(x-1, y, EffectType.BOMB);

        return toReturn;
    }

}
