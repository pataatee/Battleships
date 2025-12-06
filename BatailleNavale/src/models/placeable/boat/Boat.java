package models.placeable.boat;

import models.placeable.Placeable;
import models.placeable.PlaceableType;

import java.util.ArrayList;

public abstract class Boat extends Placeable {
    private Boolean _isAlive;
    private int _pvs;
    private ArrayList<BoatsObserver> _boatObservers;
    private BoatType _type;

    public Boat(String name,BoatType type) {
        super(name,type.getSize(), PlaceableType.BOAT);
        this._isAlive=true;
        this._pvs=type.getSize();
        this._boatObservers=new ArrayList<BoatsObserver>();
        this._type = type;
    }

    public void notifyObserver() {
        for (BoatsObserver obs : _boatObservers) {
            obs.reactOnDeath(this);
        }
    }

    public void onHit() {
        this._pvs--;
        if(_pvs<=0){
            notifyObserver();
        }
        System.out.println(_pvs);
    }

    public void addObserver(BoatsObserver ob) {
        _boatObservers.add(ob);

    }

    public BoatType getType() {
        return this._type;
    }
}
