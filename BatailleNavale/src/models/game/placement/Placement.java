package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public class Placement {

    private PlacementStrategy _placementStrategy;

    public Placement(PlacementStrategy strat) {
        this._placementStrategy = strat;
    }

    public Boolean placeObject(Placeable[] placeables, Grid grid, Coord co) {
        return this._placementStrategy.placeObjects(placeables, grid, co);
    }

    public Boolean placeOneObject(Placeable placeable, Grid grid, Coord co) {
        return this._placementStrategy.placeOneObject(placeable, grid, co);
    }

    public PlacementStrategy getPlacementStrategy() {
        return this._placementStrategy;
    }

    public void setStrat(PlacementStrategy strategy) {
        _placementStrategy = strategy;
    }


    /*
    // default values version
    // doesn't work but there's no point focusing on that rn
    public Placement(int playerId) {
        this._grids = new Grid[2];
        this._players = new Player[2];
        this._placementStrategy = new ManualPlacementStrategy(_grids[playerId], _players[playerId]);
    } */
}
