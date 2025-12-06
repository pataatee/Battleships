package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public class Placement {

    private PlacementStrategy _placementStrategy;

    //choose everything version
    public Placement(PlacementStrategy strat) {
        this._placementStrategy = strat;
    }

    public void setPlacementStrategy(PlacementStrategy strat){
        _placementStrategy=strat;
    }

    public void placeObject(Placeable[] placeables, Grid grid) {
        this._placementStrategy.placeObjects(placeables, grid);
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
