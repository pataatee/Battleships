package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

public abstract class PlacementStrategy extends PlaceableFactory {

    public PlacementStrategy() {
    }

    public abstract void placeObjects(Placeable[] placeable, Grid grid);

}
