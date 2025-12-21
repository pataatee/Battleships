package controllers;

import models.game.GameMode;
import models.game.placement.*;
import models.grid.Grid;
import models.placeable.Placeable;
import models.player.Player;

public class PlacementController {

    private Placement _pl;
    private Placeable[] _lstToPlace;
    private Coord _toPlace;
    private Grid _grid;
    private GameController _gameController;
    private Player _player;

    public PlacementController(Placement pl, Player player, GameController gc) {
        this._pl = pl;
        this._lstToPlace = player.getPlaceableList();
        this._toPlace = new Coord(-1,-1,Orientation.HORIZONTAL);//Dummy cord just to avoid Null pointer
        this._grid = player.getGrid();
        this._gameController = gc;
        this._player = player;
    }
    public void refreshList(){
        this._lstToPlace = _player.getPlaceableList();
        System.out.println("rese");
    }

    public Boolean placeObject(Placeable pla) {

        if (this._toPlace.getX() < 0 || this._toPlace.getY() < 0) {
            return false;
        }

        return this._pl.placeOneObject(pla, _grid, _toPlace);
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

        if (this._gameController.getGameMode() == GameMode.ISLAND) {
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

    public Boolean placeAllObjects() {
        return this._pl.placeObject(this._lstToPlace, this._grid, this._toPlace);
    }


    public void changeStrat(PlacementStrategies strat) {

        switch (strat) {
            case STATIC:
                this._pl.setStrat(new StaticPlacementStrategy());
                break;
            case RANDOM:
                this._pl.setStrat(new RandomPlacementStrategy());
                break;
            case MANUAL:
                this._pl.setStrat(new ManualPlacementStrategy());
                break;
        }

    }
}
