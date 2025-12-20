package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

import java.util.Random;

public class RandomPlacementStrategy extends PlacementStrategy {

    public RandomPlacementStrategy() {

    }

    @Override
    public Boolean placeObjects(Placeable[] placeable, Grid grid, Coord co) {
        Random rd = new Random();

        for (Placeable pl : placeable) {
            Boolean ok = false;

            do {
                int x = rd.nextInt(grid.getSize());
                int y = rd.nextInt(grid.getSize());
                int rota = rd.nextInt(Orientation.values().length);
                ok = grid.placeObject(pl, x,y,Orientation.values()[rota]);
            } while (!ok);
        }

        return true;
    }

    @Override
    public Boolean placeOneObject(Placeable placeable, Grid grid, Coord co) {
        Random rd = new Random();
        Boolean ok = false;
        do {
            ok = grid.placeObject(placeable, rd.nextInt(grid.getSize()), rd.nextInt(grid.getSize()),Orientation.values()[rd.nextInt(Orientation.values().length)]);
        } while (!ok);
        return true;
    }

}
