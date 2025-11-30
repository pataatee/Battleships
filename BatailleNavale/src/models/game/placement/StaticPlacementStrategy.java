package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

import java.util.ArrayList;

public class StaticPlacementStrategy extends PlacementStrategy {


    public StaticPlacementStrategy(Grid grid, Player player) {
        super(grid, player);
    }


    @Override
    public void placeObjects(Placeable[] placeable, Grid grid) {

        StaticPositions[] defaultPositions = StaticPositions.values(); // that's a java method that gets all the enum's values

        // considering that grid size is ALWAYS 10x10, boats count ALWAYS 5, traps count ALWAYS 2
        for (int i = 0; i < placeable.length ; i++) { // all 7 placeable we have to place ; better to put placeable.length than a random 7

            Placeable obj = placeable[i]; // we get the current object
            StaticPositions pos = defaultPositions[i]; // we get the current pos associated w that object

            if (grid.isTileFree(pos.getX(), pos.getY())) { // it'll be free, but better to check

                grid.placeObject(obj, pos.getX(), pos.getY(), pos.getOrientation()); // we tell grid to place the object
                                                                                     // pos.getOrientation() will be null for trap; it's okay cuz Trap doesn't have an orientation as its size is 1x1
            }

        }
    }
    // that should work but only if placeable[] is ordered the same way as the enum StaticPositions is...
    // maybe I should do a huge switch case on every single boat/trap type but ehh don't like that


}
