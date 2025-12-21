package controllers;

import models.game.GameMode;
import models.game.placement.ConfigData;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;

public class ConfigController {
    private final ConfigData _mdData;

    public ConfigController(ConfigData mdData) {
        _mdData = mdData;
    }

    /**
     * Try to select a boat for the game. If the boat cannot be added, return false
     *
     * @param boat The boat to add
     * @return true if the boat was added, false otherwise
     */
    public boolean selectBoat(Boat boat) {
        return _mdData.addBoat(boat);
    }

    /**
     * Deselect (remove) the last boat of the specified type
     *
     * @param boatType The type of boat to remove
     * @return true if a boat was removed, false otherwise
     */
    public boolean deselectBoat(BoatType boatType) {
        return _mdData.removeBoat(boatType);
    }

    /**
     * Get the total number of cells occupied by all selected boats
     *
     * @return The total number of cells
     */
    public int getTotalCases() {
        return _mdData.getTotalCells();
    }

    /**
     * Set the game mode
     *
     * @param gameMode The game mode to set
     */
    public void SelectGameMode(GameMode gameMode) {
        _mdData.setGameMode(gameMode);
    }

    /**
     * Get the current grid size
     *
     * @return The grid size
     */
    public int getGridSize() {
        return _mdData.getGridSize();
    }

    /**
     * Get the current game mode
     *
     * @return The game mode
     */
    public GameMode getGameMode() {
        return _mdData.getGameMode();
    }

    /**
     * Reset all configuration to default values
     */
    public void resetConfiguration() {
        _mdData.reset();
    }

    /**
     * Get the list of selected boats
     *
     * @return Array of selected boats
     */
    public Boat[] getSelectedBoats() {
        return _mdData.getChosenBoats().toArray(new Boat[0]);
    }

}