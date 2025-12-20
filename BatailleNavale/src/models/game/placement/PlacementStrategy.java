package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public abstract class PlacementStrategy {

    public PlacementStrategy() {
    }

    public abstract Boolean placeObjects(Placeable[] placeable, Grid grid, Coord co);

    public abstract Boolean placeOneObject(Placeable placeable, Grid grid, Coord co);

}
