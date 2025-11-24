package models.placeable.boat;

import models.placeable.Placeable;

public abstract class Boat extends Placeable {
    private Boolean _isAlive;

    public Boat(String name,int size) {
        super(name,size);
    }
}
