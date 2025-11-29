package models.game.placement;

import models.grid.Grid;
import models.placeable.PlaceableFactory;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.Player;

public abstract class PlacementStrategy extends PlaceableFactory {
    private Grid _grid;
    private Player _player;

    public PlacementStrategy(Grid grid, Player player) {
        this._grid = grid;
        this._player = player;
    }

    public abstract void placeBoats(Boat[] boats);

    public abstract void placeTraps(Trap[] traps);
}
