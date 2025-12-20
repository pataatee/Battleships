package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public class ManualPlacementStrategy extends PlacementStrategy {

    public ManualPlacementStrategy() {
    }

    @Override
    public Boolean placeObjects(Placeable[] placeable, Grid grid, Coord co) {
        return grid.placeObject(placeable[0], co.getX(), co.getY(), co.getOrientation());
    }

    @Override
    public Boolean placeOneObject(Placeable placeable, Grid grid, Coord co) {
        return grid.placeObject(placeable, co.getX(), co.getY(), co.getOrientation());
    }

}
