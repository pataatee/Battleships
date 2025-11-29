package models.game.placement;

import models.grid.Grid;
import models.player.Player;

public class Placement {

    private Grid[] _grids;
    private Player[] _players;
    private PlacementStrategy _placementStrategy;

    //choose everything version
    public Placement(Grid[] grids, Player[] players, PlacementStrategy strat) {
        this._grids = grids;
        this._players = players;
        this._placementStrategy = strat;
    }

    // only choose strategy version
    public Placement(PlacementStrategy strat) {
        this._grids = new Grid[2];
        this._players = new Player[2];
        this._placementStrategy = strat;
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
