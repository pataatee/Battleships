package controllers;

import models.player.Player;
import models.weapon.Weapon;

public class WeaponController {
    private Player _player;

    public WeaponController(Player player) {
        this._player = player;
    }

    public void setWeapon(Weapon weapon) {
        this._player.setWeapon(weapon);
    }
}
