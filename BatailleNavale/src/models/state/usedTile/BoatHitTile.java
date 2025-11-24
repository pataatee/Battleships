package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class BoatHitTile extends State {
    public BoatHitTile() {
        super(StateName.BOATHIT);
    }

    @Override
    public State onHit() {
        return this;
    }
}
