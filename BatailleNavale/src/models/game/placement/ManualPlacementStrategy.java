package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

public class ManualPlacementStrategy extends PlacementStrategy {

    public ManualPlacementStrategy() {
    }

    @Override
    public Boolean placeObjects(Placeable[] placeable, Grid grid, Coord co) {
        return grid.placeObject(placeable[0], co.getX(), co.getY(), co.getOrientation());
    }

}
