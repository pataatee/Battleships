package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class BoatDead extends State {
    public BoatDead() {
        super(StateName.BoatDead);
    }

    @Override
    public State onHit() {
        return null;
    }
}
