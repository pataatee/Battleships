package models.placeable.boat;

import models.placeable.Placeable;

import java.util.ArrayList;

public abstract class Boat extends Placeable {
    private Boolean _isAlive;
    private int _pvs;
    private ArrayList<BoatsObserver> _boatObservers;
    private BoatType _type;

    public Boat(String name,BoatType type) {
        super(name,type.getSize());
        this._isAlive=true;
        this._pvs=type.getSize();
        this._boatObservers = new ArrayList<>();
        this._type = type;
    }

    public void notifyObserver(Boolean life) {
        for (BoatsObserver obs : _boatObservers) {
            obs.notifyOnDeath(life);
        }
    }

    public void onHit() {
        this._pvs--;
    }

    public BoatType getType(){
        return _type;
    }

}
