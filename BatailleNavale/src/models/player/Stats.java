package models.player;

import models.placeable.trap.Trap;
import models.weapon.Weapon;
import models.weapon.WeaponType;

import java.util.ArrayList;

public class Stats {

    private int _nbIntactBoats;
    private int _nbHitBoats;
    private int _nbSunkBoats;

    private int _missShots;

    private int _nbHitBoatTiles;
    private int _nbBoatTilesLeft;

    private ArrayList<String> _usedItems;
    private ArrayList<String> _availableItems;

    private boolean _triggeredTornado;
    private boolean _triggeredBlackHole;

    private int _nbIslandTilesLeft;

    private ArrayList<StatsObserver> _observers;


    public Stats() {

        this._usedItems = new ArrayList<>();
        this._availableItems = new ArrayList<>();
        this._observers = new ArrayList<>();

        this._nbIntactBoats = 0;
        this._nbHitBoats = 0;
        this._nbSunkBoats = 0;
        this._missShots = 0;
        this._nbHitBoatTiles = 0;
        this._nbBoatTilesLeft = 0;
        this._triggeredTornado = false;
        this._triggeredBlackHole = false;
        this._nbIslandTilesLeft = 16;

    }

    public void setNbBoats(int nb) {
        this._nbIntactBoats = nb;
    }

    public void setNbBoatTiles(int nb) {
        this._nbBoatTilesLeft = nb;
    }

    public void addObserver(StatsObserver obs) {
        _observers.add(obs);
    }


    // notify our observers
    public void notifyObservers() {
        for (StatsObserver obs : this._observers) {
            obs.updateStats(this);
        }
    }

    // what do we update now
    // everything :D
    public void updateNbHitBoatTiles() {

        if (this._nbBoatTilesLeft > 0) {
            this._nbHitBoatTiles++;
            this._nbBoatTilesLeft--;
            notifyObservers();
        }
    }


    public void updateNbHitBoats() {
        this._nbHitBoats++;
        this._nbIntactBoats--;
        notifyObservers();
    }

    public void updateNbSunkBoats() {
        this._nbSunkBoats++;
    }

    public void updateUsedWeapon(Weapon wpn) {

        this._usedItems.add(wpn.toString());

        if (wpn.get_type() != WeaponType.MISSILE) {
            this._availableItems.remove(wpn.toString());
        }

        this.notifyObservers();
    }

    public void updateTriggeredBlackHole() {
        this._triggeredBlackHole = true;
        this._usedItems.add("Bomb");
        this.notifyObservers();
    }

    public void updateTriggeredTornado() {
        this._triggeredTornado = true;
        this._usedItems.add("Tornado");
        this.notifyObservers();
    }

    public void updateIslandTilesLeft() {
        this._nbIslandTilesLeft--;
        this.notifyObservers();
    }

    public void updateDiscoveredWeapons(Weapon wpn) {
        this._availableItems.add(wpn.toString());
        this.notifyObservers();
    }
    public void updateDiscoveredWeapons(Trap t) {
        this._usedItems.add(t.getName());
        this.notifyObservers();
    }

    public void updateMissShots() {
        this._missShots++;
        this.notifyObservers();
    }

    // === GETTERS ===

    public int getNbIntactBoats() {
        return this._nbIntactBoats;
    }

    public int getNbHitBoats() {
        return this._nbHitBoats;
    }

    public int getNbSunkBoats() {
        return this._nbSunkBoats;
    }

    public int getNbMissedShots() {
        return this._missShots;
    }

    public int getNbHitBoatTiles() {
        return this._nbHitBoatTiles;
    }

    public int getNbBoatTilesLeft() {
        return this._nbBoatTilesLeft;
    }

    public int getNbIslandTilesLeft() {
        return this._nbIslandTilesLeft;
    }

    public ArrayList<String> getUsedItems() {
        return this._usedItems;
    }

    public ArrayList<String> getAvailableItems() {
        return this._availableItems;
    }


}
