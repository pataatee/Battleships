package models.placeable.trap;

import models.placeable.Placeable;

public abstract class Trap extends Placeable {
    private Boolean _hasBeenActivated;
    private Boolean _hasBeenUsed;
    int _ownerId;

    public Trap(int ownerId) {
        this._ownerId=ownerId;
    }
}
