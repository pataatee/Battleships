package models.placeable.trap;

import models.placeable.Placeable;

public abstract class Trap extends Placeable {
    private Boolean _hasBeenActivated;
    private Boolean _hasBeenUsed;
    int _ownerId;

    public Trap(int ownerId, String name, int size) {
        super(name, size);
        this._ownerId=ownerId;
        this._hasBeenUsed=false;
        this._hasBeenActivated=false;
    }
}
