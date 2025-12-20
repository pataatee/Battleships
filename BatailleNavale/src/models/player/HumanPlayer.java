package models.player;

import models.grid.Grid;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, int id, Grid grid) {
        super(name, id, grid, PlayerType.HUMAN);
    }

    @Override
    public void notifyDeath() {
        System.out.println("Human player has been death");
    }
}