package models.player;

import models.grid.Grid;
import models.weapon.Missile;

import java.util.Random;

public class AIPlayer extends Player {
    private int _gSize;
    private Random _random;

    public AIPlayer(String name, int id, Grid grid) {
        super(name, id, grid,PlayerType.AI);
        _gSize = grid.getSize();
        _random = new Random();
        super.setWeapon(new Missile());
    }

    public int[] generateAttackCoordinates() {
        return new int[]{_random.nextInt(_gSize), _random.nextInt(_gSize)};
    }

    public Attack generateAttack() {
        int[] coords = generateAttackCoordinates();
        return createAttack(coords[0], coords[1]);
    }

    @Override
    public void notifyDeath() {
        System.out.println("Ai est morte");
    }
}