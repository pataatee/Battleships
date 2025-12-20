package models.player;

import models.weapon.Weapon;

public class Attack {

    private int _x;
    private int _y;
    private Weapon _weapon;

    public Attack(int x, int y, Weapon weapon) {
        _x = x;
        _y = y;
        _weapon = weapon;
    }

    public Weapon getWeapon() {
        return _weapon;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public String weaponToString() {
        return this._weapon.toString();
    }


}
