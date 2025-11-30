package models.game.placement;

import models.game.GameMode;
import models.grid.Grid;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;

import java.util.ArrayList;

public class ConfigData {
    private static final int MAXCELL = 35;

    private int _gridSize;
    private ArrayList<Boat> _boatList;
    private Grid[] _grids;
    private int _sumCell;
    private GameMode _gm;

    public ConfigData(Grid[] grid) {
        _grids = grid;
        _sumCell = 0;
        _gm = GameMode.NORMAL;
        _boatList = new ArrayList<Boat>();
    }

    public void setGridSize(int gridSize) {
        for (Grid grid : _grids) {
            grid.setSize(gridSize);
        }
        _gridSize = gridSize;
    }

    public int getGridSize() {
        return _gridSize;
    }

    public boolean addBoat(Boat boat) {
        _sumCell += boat.getSize();
        if (_sumCell > MAXCELL) {
            _sumCell -= boat.getSize();
            return false;
        }
        _boatList.add(boat);
        return true;
    }

    public boolean removeBoat(BoatType boatType) {
        for (int i = _boatList.size() - 1; i >= 0; i--) {
            Boat boat = _boatList.get(i);
            if (boat.getType() == boatType) {
                _boatList.remove(i);
                _sumCell -= boat.getSize();
                return true;
            }
        }
        return false;
    }

    public int getTotalCells() {
        return _sumCell;
    }

    public int getBoatCountByType(BoatType boatType) {
        int count = 0;
        for (Boat boat : _boatList) {
            if (boat.getType() == boatType) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Boat> getChosenBoats() {
        return _boatList;
    }

    public void setGameMode(GameMode gm) {
        _gm = gm;
    }

    public GameMode getGameMode() {
        return _gm;
    }
}