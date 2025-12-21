package models.game.placement;

import models.game.GameMode;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;
import models.player.Player;

import java.util.ArrayList;

public class ConfigData {

    private int gridSize;
    private int sumCell;
    private GameMode gameMode;

    private final Player _player1;
    private final Player _player2;

    private final ArrayList<Boat> boatList;

    public ConfigData(Player player1, Player player2) {
        this._player1 = player1;
        this._player2 = player2;
        this.sumCell = 0;
        this.gameMode = GameMode.NORMAL;
        this.gridSize = 10; // Taille par défaut
        this.boatList = new ArrayList<>();

        // Initialiser les grilles avec la taille par défaut
        _player1.getGrid().setSize(gridSize);
        _player2.getGrid().setSize(gridSize);
    }

    public int getMaxAllowedCells() {
        return 35;
    }

    /**
     * Set the grid size
     *
     * @param gridSize The new grid size
     */
    public void setGridSize(int gridSize) {
        if (gridSize < 6 || gridSize > 10) {
            throw new IllegalArgumentException("La taille de la grille doit être entre 6 et 10");
        }

        this.gridSize = gridSize;

        // Mettre à jour les grilles des joueurs
        _player1.getGrid().setSize(gridSize);
        _player2.getGrid().setSize(gridSize);

        // Réinitialiser la liste des bateaux si la nouvelle taille est trop petite
        if (sumCell > getMaxAllowedCells()) {
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
        int newTotal = sumCell + boat.getSize();
        int maxAllowed = getMaxAllowedCells();

        if (newTotal > maxAllowed) {
            return false;
        }

        sumCell = newTotal;
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
        int newTotal = sumCell + boat.getSize();
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
                sumCell -= boat.getSize();
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
        return sumCell;
    }

    /**
     * Get the count of boats of a specific type
     *
     * @param boatType The type of boat
     * @return The count of boats
     */
    public int getBoatCountByType(BoatType boatType) {
        int count = 0;
        for (Boat boat : boatList) {
            if (boat.getType() == boatType) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the list of chosen boats
     *
     * @return The list of boats
     */
    public ArrayList<Boat> getChosenBoats() {
        return new ArrayList<>(boatList); // Retourne une copie pour éviter la modification externe
    }

    /**
     * Set the game mode
     *
     * @param gameMode The game mode to set
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

        // Réinitialiser la liste des bateaux si la nouvelle configuration est trop restrictive
        if (sumCell > getMaxAllowedCells()) {
            clearExcessBoats();
        }
    }

    /**
     * Get the current game mode
     *
     * @return The game mode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Get the current grid size
     *
     * @return The grid size
     */
    public int getGridSize() {
        return gridSize;
    }


    /**
     * Clear excess boats when configuration changes
     */
    private void clearExcessBoats() {
        int maxAllowed = getMaxAllowedCells();

        while (sumCell > maxAllowed && !boatList.isEmpty()) {
            Boat lastBoat = boatList.remove(boatList.size() - 1);
            sumCell -= lastBoat.getSize();
        }
    }

    /**
     * Reset the configuration to default values
     */
    public void reset() {
        this.boatList.clear();
        this.sumCell = 0;
        this.gameMode = GameMode.NORMAL;
        this.gridSize = 10;

        _player1.getGrid().setSize(gridSize);
        _player2.getGrid().setSize(gridSize);
    }

}