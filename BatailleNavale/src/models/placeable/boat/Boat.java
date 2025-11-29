package models.placeable.boat;

import models.placeable.Placeable;

import java.util.ArrayList;

public abstract class Boat extends Placeable {
    private Boolean _isAlive;
    private int _pvs;
    private ArrayList<BoatsObserver> boatObservers;

    public Boat(String name,int size) {
        super(name,size);
        this._isAlive=true;
        this._pvs=size;
        this.boatObservers = new ArrayList<>();
    }

    public void notifyObserver(Boolean life) {
        for (BoatsObserver obs : boatObservers) {
            obs.notifyOnDeath(life);
        }
    }

    public void onHit() {
        this._pvs--;
    }
}
