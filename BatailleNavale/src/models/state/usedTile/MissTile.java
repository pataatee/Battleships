package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class MissTile extends State {

    public MissTile() {
        super(StateName.MISS);
    }

    @Override
    public State onHit() {
        return this;
    }
}
