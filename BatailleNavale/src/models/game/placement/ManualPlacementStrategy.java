package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

public class ManualPlacementStrategy extends PlacementStrategy {

    public ManualPlacementStrategy(Grid grid, Player player) {
        super(grid, player);
    }

    @Override
    public void placeObjects(Placeable[] placeable, Grid grid) {

    }

}
