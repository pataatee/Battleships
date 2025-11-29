package models.game.placement;

import models.grid.Grid;
import models.placeable.PlaceableFactory;
import models.player.Player;

public abstract class PlacementStrategy extends PlaceableFactory {
    private Grid _grid;
    private Player _player;
}
