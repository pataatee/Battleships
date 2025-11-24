package models.grid;

import models.state.State;

public interface TileObserver {
    void update_tile(State state);
}
