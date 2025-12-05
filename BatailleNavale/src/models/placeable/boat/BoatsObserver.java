package models.placeable.boat;


import models.grid.TileState;

public interface BoatsObserver {
    public void notifyOnDeath(Boolean life);
    public void updateTileState(TileState state);
}
