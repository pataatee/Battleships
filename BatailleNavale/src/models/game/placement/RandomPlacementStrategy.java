package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

import java.util.Random;

public class RandomPlacementStrategy extends PlacementStrategy {

    public RandomPlacementStrategy() {

    }

    @Override
    public void placeObjects(Placeable[] placeable, Grid grid) {
        Random rd = new Random();

        for (Placeable pl : placeable) {
            Boolean ok = false;
            do {
                ok = grid.placeObject(pl, rd.nextInt(grid.getSize()), rd.nextInt(grid.getSize()),Orientation.values()[rd.nextInt(Orientation.values().length)]);
            } while (!ok);
        }
    }

}
