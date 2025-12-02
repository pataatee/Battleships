package models.weapon;

public class WeaponFactory {

    public WeaponFactory() {

    }

    public Weapon createMissile() {
        return new Missile();
    }

    public Weapon createSonar() {
        return new Sonar();
    }

    public Weapon createBomb() {
        return new Bomb();
    }

}
