package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public abstract class PlacementStrategy {

    public PlacementStrategy() {
    }

    public abstract void placeObjects(Placeable[] placeable, Grid grid, Coord co);

}
