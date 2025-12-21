package models.grid;

import models.game.placement.Orientation;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.player.ShotResult;
import models.player.ShotResultType;
import models.weapon.WeaponType;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private int _size;
    private Tile[][] _tilesMap;
    private ArrayList<GridObserver> _observer;

    public Grid(int size) {
        _observer = new ArrayList<GridObserver>();
        _size = size;
        generateGrid();
    }

    /**
     * Set the grid size and regenerate it
     *
     * @param size
     */
    public void setSize(int size) {
        _size = size;
        generateGrid();
    }


    /**
     * Generate the grid with size: _size
     */
    public void generateGrid() {
        _tilesMap = new Tile[_size][_size];

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                _tilesMap[i][j] = new SeaTile();
            }
        }
    }

    /**
     * Change the TileState of the Tile
     *
     * @param x        position X of  the tile
     * @param y        position Y of the tile
     * @param newState new state of the tile
     */
    public void changeStateOfTile(int x, int y, TileState newState) {
        if (x >= 0 && x < _size && y >= 0 && y < _size) {
            _tilesMap[x][y].setState(newState);
            notifyObserver(x, y, _tilesMap[x][y].getStateName());
        }
    }

    public ShotResult hitTile(int x, int y) {
        if (x < 0 || x >= _size || y < 0 || y >= _size) {
            return new ShotResult(x, y, ShotResultType.MISS);
        }
        ShotResult res = _tilesMap[x][y].onHit(x, y);
        notifyObserver(x, y, _tilesMap[x][y].getStateName());
        return res;
    }


    /**
     * Check if the tile x,y is free (TileState = Empty)
     *
     * @param x Position X
     * @param y Ta mère
     * @return false if tile is not free or if the asked cord are of grid
     */
    public boolean isTileFree(int x, int y) {
        if (x >= 0 && x < _size && y >= 0 && y < _size) {
            return _tilesMap[x][y].isFree();
        }
        return false;
    }

    /**
     * Subscribe a new observer
     *
     * @param ob the new subscriber
     */
    public void addObserver(GridObserver ob) {
        _observer.add(ob);
    }

    public void notifyObserver(int x, int y, TileState state) {
        for (GridObserver ob : _observer) {
            ob.updateTileState(x, y, state);
        }
    }

    public Boolean placeObject(Placeable object, int x, int y, Orientation orientation) {
        int[][] positions = object.skibidiRizzler(x, y, orientation);

        for (int[] position : positions) {
            if (!isTileFree(position[0], position[1])) {
                return false;
            }
        }

        if (object.getPlaceableType() == PlaceableType.BOAT) {
            Boat boat = (Boat) object;
            for (int[] position : positions) {
                changeStateOfTile(position[0], position[1], TileState.BOAT);
                _tilesMap[position[0]][position[1]].setObject(object);
                boat.addPosition(new int[]{position[0], position[1]});
            }
        } else if (object.getPlaceableType() == PlaceableType.TRAP) {
            for (int[] position : positions) {
                changeStateOfTile(position[0], position[1], TileState.TRAP);
                _tilesMap[position[0]][position[1]].setObject(object);
            }
        }

        return true;
    }

    public int getSize() {
        return this._size;
    }


    public void setUpIsland() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                _tilesMap[i + 3][j + 3] = new IslandTile();
                notifyObserver(i + 3, j + 3, _tilesMap[i + 3][j + 3].getStateName());
            }
        }

        Random random = new Random();

        int x1 = random.nextInt(4);
        int y1 = random.nextInt(4);

        int x2, y2;
        do {
            x2 = random.nextInt(4);
            y2 = random.nextInt(4);
        } while (x1 == x2 && y1 == y2);
        ((IslandTile)_tilesMap[x1+3][y1+3]).addWeapon(WeaponType.BOMB);
        ((IslandTile)_tilesMap[x2+3][y2+3]).addWeapon(WeaponType.BOMB);
    }



    public void addWeaponToIslandTile(int x, int y, WeaponType wp) {
        if (_tilesMap[x][y].getStateName() == TileState.ISLAND) {
            ((IslandTile) _tilesMap[x][y]).addWeapon(wp);
        }
    }

    public WeaponType getWeaponFromIslandTile(int x, int y) {
        if (_tilesMap[x][y].getStateName() == TileState.ISLANDHIT) {
            return ((IslandTile) _tilesMap[x][y]).getWeapon();
        }
        return null;
    }


    public TileState getTileTileState(int x, int y) {
        if (x < 0 || x >= _size || y < 0 || y >= _size) {
            return TileState.EMPTY;
        }
        return _tilesMap[x][y].getStateName();
    }

    public void resetGrid() {
        this.generateGrid();
        this.notifyReset();
    }


    public void notifyReset() {
        for (int x = 0; x < this._size; x++) {
            for (int y = 0; y < this._size; y++) {
                notifyObserver(x, y, this._tilesMap[x][y].getStateName());
            }
        }
    }

}