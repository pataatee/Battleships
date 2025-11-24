package models.placeable.boat;

import models.placeable.Placeable;

public abstract class Boat extends Placeable {
    private Boolean _isAlive;
    private int _pvs;

    public Boat(String name,int size) {
        super(name,size);
        this._isAlive=true;
        this._pvs=size;
    }
}
