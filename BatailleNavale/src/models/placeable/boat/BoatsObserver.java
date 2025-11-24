package models.placeable.boat;

import models.state.State;

public interface BoatsObserver {
    public void notifyOnDeath(Boolean life);
    public void updateTileState(State state);
}
