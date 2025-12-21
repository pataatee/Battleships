package models.player;

import models.game.logs.GameLogs;
import models.game.logs.Log;
import models.grid.Grid;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, int id, Grid grid) {
        super(name, id, grid, PlayerType.HUMAN);
    }

    @Override
    public void notifyDeath(GameLogs log) {
        log.addLog(new Log("Player " + super.getName() + ", ID: " + super.getId() + " has been defeated !"));
        System.out.println("Human player has been death");
    }
}