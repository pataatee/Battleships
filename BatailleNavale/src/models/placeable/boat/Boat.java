package models.placeable.boat;

import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.player.ShotResult;
import models.player.ShotResultType;

public abstract class Boat extends Placeable {
    private Boolean _isDead;
    private int _pvs;
    private int[][] _position;
    private BoatType _type;
    private int _positionIndex;
    private boolean _isFirstHit;
    private int _size;

    public Boat(String name, BoatType type) {
        super(name, type.getSize(), PlaceableType.BOAT);
        this._isDead = false;
        this._pvs = type.getSize();
        this._size = type.getSize();
        this._type = type;
        _position = new int[type.getSize()][2];
        this._isFirstHit = false;
    }

    public ShotResult onHit(int x, int y) {
        if (this._size == this._pvs) {
            this._isFirstHit = true;
        }
        this._pvs--;
        if (_pvs <= 0) {
            _isDead = true;
            return new ShotResult(x, y, ShotResultType.SUNK);
        }
        return new ShotResult(x, y, ShotResultType.HIT);
    }

    public void addPosition(int[] pos) {
        _position[_positionIndex] = pos;
        _positionIndex++;
    }

    public int[][] getPosition() {
        return _position;
    }

    public BoatType getType() {
        return this._type;
    }


    public boolean isDead() {
        return _isDead;
    }

    @Override
    public void resetPositions() {
        this._positionIndex = 0;
        this._pvs = this.getSize();
        this._isDead = false;

        for (int i = 0; i < this._position.length; i++) {
            this._position[i] = null;
        }
    }

    public boolean getIsFirstHit() {
        return this._isFirstHit;
    }

    public void setIsFirstHit(boolean is) {
        this._isFirstHit = is;
    }

}
