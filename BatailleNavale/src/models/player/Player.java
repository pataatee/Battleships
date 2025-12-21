package models.player;

import models.game.logs.GameLogs;
import models.game.logs.Log;
import models.grid.Grid;
import models.grid.TileState;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.*;

import java.util.ArrayList;

public abstract class Player {
    private int _id;
    private String _name;
    private boolean _isAlive = true;
    private ArrayList<Boat> _boatList;
    private ArrayList<Trap> _trapList;
    private Grid _grid;
    private Weapon _currentWeapon;
    private ArrayList<WeaponType> _WeaponList;
    private PlayerType _type;
    private ArrayList<WeaponObserver> _wpObserverList;
    private int _isTornaded;
    private Stats _stats;


    public Player(String name, int id, Grid grid, PlayerType type) {
        _id = id;
        _name = name;
        _grid = grid;
        _boatList = new ArrayList<>();
        _trapList = new ArrayList<>();
        _currentWeapon = new Missile();
        _WeaponList = new ArrayList<>();
        _type = type;
        _wpObserverList = new ArrayList<>();

        this._stats = new Stats();
    }

    public void addBoat(Boat boat) {
        _boatList.add(boat);
    }

    ;

    public void addTrap(Trap trap) {
        _trapList.add(trap);

    }

    ;

    public Attack createAttack(int x, int y) {

        int xoffset = x;
        int yoffset = y;
        if (_isTornaded > 0) {
            _isTornaded--;
            xoffset = (5 + xoffset) % (this._grid.getSize() - 1);
            yoffset = (5 + yoffset) % (this._grid.getSize() - 1);
        }
        return new Attack(xoffset, yoffset, _currentWeapon);
    }

    public ShotResult[] getAttacked(Attack attack) {
        int x = attack.getX();
        int y = attack.getY();
        Weapon weapon = attack.getWeapon();
        Effect[] effect = weapon.use(x, y);
        ShotResult[] res = new ShotResult[effect.length];
        int i = 0;
        for (Effect value : effect) {
            int effectx = value.getPos()[0];
            int effecty = value.getPos()[1];
            switch (value.getEffectType()) {
                case HIT -> {
                    res[i] = _grid.hitTile(effectx, effecty);
                }
                case SCAN -> {
                    res[i] = _grid.getTileTileState(effectx, effecty) == TileState.EMPTY || _grid.getTileTileState(effectx, effecty) == TileState.ISLAND ? new ShotResult(effectx, effecty, ShotResultType.MISS) : new ShotResult(effectx, effecty, ShotResultType.SONAR);
                }
                case BOMB -> {
                    if (_grid.getTileTileState(effectx, effecty) == TileState.ISLAND) {
                        res[i] = new ShotResult(effectx, effecty, ShotResultType.MISS);
                    } else {
                        res[i] = _grid.hitTile(effectx, effecty);
                    }

                }
            }
            if (res[i].get_type() == ShotResultType.SUNK) {
                reactToSunk();
            }
            i++;
        }
        return res;
    }


    public void setWeapon(Weapon weapon) {
        if (_WeaponList.contains(weapon.get_type())) {
            _currentWeapon = weapon;
            System.out.println("new weapon added" + weapon);

        } else {
            _currentWeapon = new Missile();
        }
        notifyWeaponSelected(_currentWeapon.get_type());
    }

    public void addWeapon(WeaponType wt) {
        _WeaponList.add(wt);
        System.out.println(wt);
        notifyWeaponUnlocked(wt, true);
    }

    public void removeWeapon(WeaponType wt) {
        if (wt == WeaponType.MISSILE) {
            return;
        }
        _WeaponList.remove(wt);
        setWeapon(new Missile());
        notifyWeaponUnlocked(wt, false);
    }


    public Weapon getWeapon() {
        return _currentWeapon;
    }


    public boolean isAlive() {
        return _isAlive;
    }

    public PlayerType getType() {
        return _type;
    }


    public void reactToSunk() {
        int i = 0;
        for (Boat boat : _boatList) {
            if (boat.isDead()) {
                i++;
                int[][] positions = boat.getPosition();
                for (int[] pos : positions) {
                    _grid.changeStateOfTile(pos[0], pos[1], TileState.BOATDEAD);
                }
            }
        }
        if (i == _boatList.size()) {
            _isAlive = false;
        }
    }


    public void addPlaceable(Placeable[] placeable) {
        int totalBoatTiles = 0;
        int totalBoats = 0;
        for (Placeable pl : placeable) {
            if (pl.getPlaceableType() == PlaceableType.BOAT) {
                totalBoats++;
                totalBoatTiles += pl.getSize();
                this.addBoat((Boat) pl);
            }
            if (pl.getPlaceableType() == PlaceableType.TRAP) {
                this.addTrap((Trap) pl);
            }

        }

        this._stats.setNbBoats(totalBoats);
        this._stats.setNbBoatTiles(totalBoatTiles);

    }

    public void setUpIsland() {
        _grid.setUpIsland();
    }

    public Grid getGrid() {
        return _grid;
    }


    public void addWeaponObserver(WeaponObserver wo) {
        _wpObserverList.add(wo);
    }

    public void notifyWeaponSelected(WeaponType wp) {
        for (WeaponObserver wo : _wpObserverList) {
            wo.notifySelected(wp);
        }
    }

    public void notifyWeaponUnlocked(WeaponType wp, boolean unlock) {
        for (WeaponObserver wo : _wpObserverList) {
            wo.notifyUnlocked(wp, unlock);
        }
    }


    public void handelShotResult(ShotResult[] resultTypes, GameLogs logs) {
        int sonarResult = 0;
        for (ShotResult res : resultTypes) {
            switch (res.get_type()) {
                case HIT -> {
                    logs.addLog(new Log(this, "Hit boat at (" + res.get_x() + "," + res.get_y() + ") !"));
                }
                case SUNK -> {
                    logs.addLog(new Log(this, "Hit and sunk boat at (" + res.get_x() + "," + res.get_y() + ") !"));
                }
                case SONAR -> {
                    sonarResult++;
                    logs.addLog(new Log(this, "Scanned boat(s) around (" + res.get_x() + "," + res.get_y() + ") !"));
                }
                case TORNAD -> {
                    logs.addLog(new Log(this, "Triggered Tornado trap at (" + res.get_x() + "," + res.get_y() + ") ! The next 3 attacks are scrambled."));
                    _isTornaded = 3;
                    this._stats.updateTriggeredTornado();
                }
                case BLACKHOLE -> {
                    logs.addLog(new Log(this, "Triggered Blackhole trap at (" + res.get_x() + "," + res.get_y() + ") ! Attack backfired."));
                    this.getAttacked(createAttack(res.get_x(), res.get_y()));
                    this._stats.updateTriggeredBlackHole();
                }
                case ISLANDHIT -> {
                    logs.addLog(new Log(this, "Searched the Island at (" + res.get_x() + "," + res.get_y() + ") ! Nothing has been discovered."));
                }
                case DISCOVERBOMB -> {
                    logs.addLog(new Log(this, "Searched the Island at (" + res.get_x() + "," + res.get_y() + ") ! Found the Bomb weapon!"));
                    this.addWeapon(WeaponType.BOMB);
                    this._stats.updateDiscoveredWeapons(new Bomb());
                }
                case DISCOVERSONAR -> {
                    logs.addLog(new Log(this, "Searched the Island at (" + res.get_x() + "," + res.get_y() + ") ! Found the sonar!"));
                    this.addWeapon(WeaponType.SONAR);
                    this._stats.updateDiscoveredWeapons(new Sonar());
                }
                default -> {
                    logs.addLog(new Log(this, "Shot at (" + res.get_x() + "," + res.get_y() + ") ! There's nothing on this tile."));
                }
            }
        }

        if (sonarResult != 0) {
            System.out.println("Sonar " + sonarResult);
        }

        // Gestion de l'arme utilisée
        this._stats.updateUsedWeapon(this._currentWeapon);
        removeWeapon(_currentWeapon.get_type());
    }


    public String getName() {
        return this._name;
    }

    public int getId() {
        return this._id;
    }


    public abstract void notifyDeath(GameLogs logs);

    public Boat getBoatAt(int x, int y) {
        System.out.println("=== DEBUG getBoatAt(" + x + ", " + y + ") ===");
        System.out.println("Total boats in list: " + this._boatList.size());

        for (Boat boat : this._boatList) {
            System.out.println("Checking boat: " + boat);
            int[][] positions = boat.getPosition();
            System.out.println("Boat has " + (positions != null ? positions.length : "null") + " positions");

            if (positions != null) {
                for (int i = 0; i < positions.length; i++) {
                    int[] pos = positions[i];
                    if (pos != null) {
                        System.out.println("  Position[" + i + "]: (" + pos[0] + ", " + pos[1] + ")");
                        if (pos[0] == x && pos[1] == y) {
                            System.out.println("  FOUND MATCH!");
                            return boat;
                        }
                    } else {
                        System.out.println("  Position[" + i + "]: null");
                    }
                }
            }
        }

        System.out.println("NO BOAT FOUND at (" + x + ", " + y + ")");
        System.out.println("Grid tile state: " + _grid.getTileTileState(x, y));
        return null;
    }

    public Stats getStats() {
        return this._stats;
    }

    public int getNbBoats() {
        return this._boatList.size();
    }

    public int getNbBoatTiles() {
        int total = 0;
        for (Boat boat : this._boatList) {
            total += boat.getSize();
        }
        return total;
    }
}
