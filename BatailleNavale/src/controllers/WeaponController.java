package controllers;

import models.player.Player;
import models.weapon.Bomb;
import models.weapon.Missile;
import models.weapon.Sonar;
import models.weapon.Weapon;

public class WeaponController {
    private Player _player;

    public WeaponController(Player player) {
        this._player = player;
    }


    public void setBomb() {
        this._player.setWeapon(new Bomb());
    }

    public void setMissile() {
        this._player.setWeapon(new Missile());
    }

    public void setSonar() {
        this._player.setWeapon(new Sonar());
    }

}
