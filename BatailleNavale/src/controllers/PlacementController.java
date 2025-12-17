package controllers;

import models.game.GameMode;
import models.game.placement.Coord;
import models.game.placement.Orientation;
import models.game.placement.Placement;
import models.grid.Grid;
import models.placeable.Placeable;

public class PlacementController {

    private Placement _pl;
    private Placeable[] _lstToPlace;
    private Coord _toPlace;
    private Grid _grid;
    private GameMode _gameMode;

    public PlacementController(Placement pl, Placeable[] lst, Coord co,Grid grid, GameMode gm) {
        this._pl = pl;
        this._lstToPlace = lst;
        this._toPlace = co;
        _grid = grid;
        this._gameMode = gm;
    }

    public Boolean placeObject(Placeable pla) {
        Placeable[] pl1 = new Placeable[1];
        pl1[0] = pla;
        return this._pl.placeObject(pl1,_grid,_toPlace);
    }


    public int getNbPlaceables() {
        return this._lstToPlace.length;
    }

    public String getPlName(int i) {
        return this._lstToPlace[i].getName();
    }

    public Placeable getPl(int i) {
        return this._lstToPlace[i];
    }

    public void setCoordOr(Orientation or) {
        this._toPlace.setOrientation(or);
    }

    public Orientation getCoordOr() {
        return this._toPlace.getOrientation();
    }

    public void setCoordXY(int x, int y) {
        this._toPlace.setX(x);
        this._toPlace.setY(y);
    }

    public void setCoord(Coord co) {
        this._toPlace = co;
    }

    public String showCoord() {
        return this._toPlace.toString();
    }

    public void resetPlacement() {
        for (Placeable pl : this._lstToPlace) {
            pl.resetPositions();
        }

        this._grid.resetGrid();

        if (this._gameMode == GameMode.ISLAND) {
            this._grid.setUpIsland();
        }

        this._grid.notifyReset();

        this._toPlace.setX(0);
        this._toPlace.setY(0);
        this._toPlace.setOrientation(Orientation.HORIZONTAL);

    }

    public Placeable getPlFromIndex(int i) {
        if (i == -1) {
            return null;
        }
        return this._lstToPlace[i];
    }

    public String getPlNameFromIndex(int i) {
        return this._lstToPlace[i].getName();
    }

    public void setPlaceableAtIndex(Placeable pl, int i) {
        this._lstToPlace[i] = pl;
    }


}
