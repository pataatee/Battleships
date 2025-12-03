package models.player;

import models.grid.Grid;
import models.weapon.Weapon;

import java.util.Random;

public class AIPlayer extends Player{
    private int _gSize;
    public AIPlayer(String name, int id, Grid grid) {
        super(name, id, grid);
        _gSize = grid.getSize();
    }

    @Override
    public Attack attack(int x, int y, Weapon weapon) {
        Random rd = new Random();
        return new Attack(rd.nextInt(_gSize),rd.nextInt(_gSize),getWeapon());
    }

    public Attack randomAttack() {
        Random rd = new Random();
        System.out.println(getWeapon());
        return attack(rd.nextInt(_gSize),rd.nextInt(_gSize),getWeapon());
    }
}
