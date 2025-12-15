package models.placeable.trap;

import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.player.ShotResult;

public abstract class Trap extends Placeable {
    private Boolean _hasBeenActivated;
    private Boolean _hasBeenUsed;
    int _ownerId;

    public Trap(int ownerId, String name, int size) {
        super(name, size, PlaceableType.TRAP);
        this._ownerId=ownerId;
        this._hasBeenUsed=false;
        this._hasBeenActivated=false;
    }

    @Override
    public abstract ShotResult onHit(int x,int y);

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
