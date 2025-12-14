package controllers;

import models.game.placement.Placement;
import models.placeable.Placeable;

public class PlacementController {

    private Placement _pl;
    private Placeable[] _lstToPlace;

    public PlacementController(Placement pl, Placeable[] lst) {
        this._pl = pl;
        this._lstToPlace = lst;
    }

    public void placeOneObject() {

    }

    public int getNbPlaceables() {
        return this._lstToPlace.length;
    }

    public String getPlName(int i) {
        return this._lstToPlace[i].getName();
    }

}
