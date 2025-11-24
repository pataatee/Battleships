package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class Miss_tile extends State {

    public Miss_tile() {
        super(StateName.MISS);
    }

    @Override
    public State onHit() {
        return this;
    }
}
