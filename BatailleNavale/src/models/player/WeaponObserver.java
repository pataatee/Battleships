package models.player;

import models.weapon.WeaponType;

public interface WeaponObserver {

    public void notifySelected(WeaponType wp);

    public void notifyUnlocked(WeaponType wp, boolean unlocked);
}
