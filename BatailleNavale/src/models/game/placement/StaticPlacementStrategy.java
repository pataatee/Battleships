package models.game.placement;

import models.grid.Grid;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

public class StaticPlacementStrategy extends PlacementStrategy {

    public StaticPlacementStrategy(Grid grid, Player player) {
        super(grid, player);
    }

    @Override
    public void placeBoats(Boat[] boats) {

    }

    @Override
    public void placeTraps(Trap[] traps) {

    }


}
