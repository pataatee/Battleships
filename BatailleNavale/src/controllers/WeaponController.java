package controllers;

import models.player.Player;
import models.weapon.WeaponFactory;

public class WeaponController {
    private Player _player;
    private WeaponFactory wp;
    public WeaponController(Player player) {
        this._player = player;
        this.wp = new WeaponFactory();
    }


    public void setBomb() {
        this._player.setWeapon(wp.createBomb());
    }

    public void setMissile() {
        this._player.setWeapon(wp.createMissile());
    }

    public void setSonar() {
        this._player.setWeapon(wp.createSonar());
    }

}
