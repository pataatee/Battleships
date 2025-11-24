package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class TrapUsedTile extends State {
    public TrapUsedTile() {
        super(StateName.TRAPUSED);
    }

    @Override
    public State onHit() {

    }
}
