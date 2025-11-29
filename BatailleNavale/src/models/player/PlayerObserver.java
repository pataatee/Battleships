package models.player;

import models.weapon.Weapon;

public interface PlayerObserver {
    public void notifyShoot(int x, int y, Weapon weapon);
    public void notifyHit(ShotResult[] result);
    public void notifyEndTurn();
    public void notifyDeath(boolean death);

}
