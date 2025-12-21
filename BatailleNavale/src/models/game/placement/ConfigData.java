package models.game.placement;

import models.game.GameMode;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;
import models.player.Player;

import java.util.ArrayList;

public class ConfigData {

    private int _gridSize;
    private int _sumCell;
    private GameMode _gameMode;

    private final Player _player1;
    private final Player _player2;

    private final ArrayList<Boat> boatList;

    public ConfigData(Player player1, Player player2) {
        this._player1 = player1;
        this._player2 = player2;
        this._sumCell = 0;
        this._gameMode = GameMode.NORMAL;
        this._gridSize = 10; // Taille par défaut
        this.boatList = new ArrayList<>();

        _player1.getGrid().setSize(_gridSize);
        _player2.getGrid().setSize(_gridSize);
    }

    public int getMaxAllowedCells() {
        return 35;
    }

    /**
     * Set the grid size
     *
     * @param _gridSize The new grid size
     */
    public void set_gridSize(int _gridSize) {
        if (_gridSize < 6 || _gridSize > 10) {
            throw new IllegalArgumentException("La taille de la grille doit être entre 6 et 10");
        }

        this._gridSize = _gridSize;

        _player1.getGrid().setSize(_gridSize);
        _player2.getGrid().setSize(_gridSize);

        if (_sumCell > getMaxAllowedCells()) {
            clearExcessBoats();
        }
    }

    /**
     * Add a boat to the configuration
     *
     * @param boat The boat to add
     * @return true if the boat was added, false otherwise
     */
    public boolean addBoat(Boat boat) {
        int newTotal = _sumCell + boat.getSize();
        int maxAllowed = getMaxAllowedCells();

        if (newTotal > maxAllowed) {
            return false;
        }

        _sumCell = newTotal;
        boatList.add(boat);
        return true;
    }

    /**
     * Check if a boat can be added without actually adding it
     *
     * @param boat The boat to check
     * @return true if the boat can be added, false otherwise
     */
    public boolean canAddBoat(Boat boat) {
        int newTotal = _sumCell + boat.getSize();
        return newTotal <= getMaxAllowedCells();
    }

    /**
     * Remove the last boat of the specified type
     *
     * @param boatType The type of boat to remove
     * @return true if a boat was removed, false otherwise
     */
    public boolean removeBoat(BoatType boatType) {
        for (int i = boatList.size() - 1; i >= 0; i--) {
            Boat boat = boatList.get(i);
            if (boat.getType() == boatType) {
                boatList.remove(i);
                _sumCell -= boat.getSize();
                return true;
            }
        }
        return false;
    }

    /**
     * Get the total number of cells occupied by selected boats
     *
     * @return The total number of cells
     */
    public int getTotalCells() {
        return _sumCell;
    }

    /**
     * Get the list of chosen boats
     *
     * @return The list of boats
     */
    public ArrayList<Boat> getChosenBoats() {
        return new ArrayList<>(boatList);
    }

    /**
     * Set the game mode
     *
     * @param _gameMode The game mode to set
     */
    public void set_gameMode(GameMode _gameMode) {
        this._gameMode = _gameMode;

        // Réinitialiser la liste des bateaux si la nouvelle configuration est trop restrictive
        if (_sumCell > getMaxAllowedCells()) {
            clearExcessBoats();
        }
    }

    /**
     * Get the current game mode
     *
     * @return The game mode
     */
    public GameMode get_gameMode() {
        return _gameMode;
    }

    /**
     * Get the current grid size
     *
     * @return The grid size
     */
    public int get_gridSize() {
        return _gridSize;
    }


    /**
     * Clear excess boats when configuration changes
     */
    private void clearExcessBoats() {
        int maxAllowed = getMaxAllowedCells();

        while (_sumCell > maxAllowed && !boatList.isEmpty()) {
            Boat lastBoat = boatList.remove(boatList.size() - 1);
            _sumCell -= lastBoat.getSize();
        }
    }

    /**
     * Reset the configuration to default values
     */
    public void reset() {
        this.boatList.clear();
        this._sumCell = 0;
        this._gameMode = GameMode.NORMAL;
        this._gridSize = 10;

        _player1.getGrid().setSize(_gridSize);
        _player2.getGrid().setSize(_gridSize);
    }

}