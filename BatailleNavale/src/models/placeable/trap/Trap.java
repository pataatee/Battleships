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

    @Override
    public abstract void onHit();

    public void setUsed(Boolean used) {
        this._hasBeenUsed=used;
    }

    public void setActivated(Boolean activation) {
        this._hasBeenActivated=activation;
    }

    public Boolean getActivation() {
        return this._hasBeenActivated;
    }
}
