package models.player;

import models.grid.Grid;
import models.grid.TileState;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.Effect;
import models.weapon.Missile;
import models.weapon.Weapon;
import models.weapon.WeaponType;

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
    }

    public void addBoat(Boat boat) {
        _boatList.add(boat);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
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
        for (Placeable pl : placeable) {
            if (pl.getPlaceableType() == PlaceableType.BOAT) {
                this.addBoat((Boat) pl);
            }
            if (pl.getPlaceableType() == PlaceableType.TRAP) {
                this.addTrap((Trap) pl);
            }

        }

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


    public void handelShotResult(ShotResult[] resultTypes) {
        int sonarResult = 0;
        for (ShotResult res : resultTypes) {
            switch (res.get_type()) {
                case HIT -> {
                    System.out.println("Add to log Hit");
                }
                case SUNK -> {
                    System.out.println("Add to log Sunk");
                }
                case SONAR -> {
                    sonarResult++;
                }
                case TORNAD -> {
                    System.out.println("Add to log Tornadoed");
                    _isTornaded = 3;
                }
                case BLACKHOLE -> {
                    System.out.println("Add to log BlackHole");
                    this.getAttacked(createAttack(res.get_x(), res.get_y()));
                }
                case ISLANDHIT -> {
                    System.out.println("Add to log IslandHit");
                }
                case DISCOVERBOMB -> {
                    System.out.println("Add to log DiscoverBomb");
                    this.addWeapon(WeaponType.BOMB);
                }
                case DISCOVERSONAR -> {
                    this.addWeapon(WeaponType.SONAR);
                }
                default -> {
                    System.out.println("Add to log Miss");
                }
            }
        }
        if (sonarResult != 0) {
            System.out.println("Sonar" + sonarResult);
        }
        removeWeapon(_currentWeapon.get_type());
    }


    public abstract void notifyDeath();

    public Placeable[] getPlaceableList() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        int totalSize = _boatList.size() + _trapList.size();
        Placeable[] result = new Placeable[totalSize];
        int index = 0;
        for (Boat boat : _boatList) {
            result[index++] = boat;
            System.out.println(boat);
        }

        for (Trap trap : _trapList) {
            result[index++] = trap;
        }
        return result;
    }

}
