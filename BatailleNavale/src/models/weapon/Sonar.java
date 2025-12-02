package models.weapon;

public class Sonar implements Weapon {

    public Sonar() {

    }

    public Effect[] use(int x, int y) {
        Effect[] toReturn = new Effect[9];
        x = x - 1;
        y = y - 1;
        int index = 0;
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                Effect effect = new Effect(x+i, y+j, EffectType.SCAN);
                toReturn[index] = effect;
                index++;
            }
        }
        return toReturn;
    }

}
