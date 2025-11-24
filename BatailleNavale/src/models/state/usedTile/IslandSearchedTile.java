package models.state.usedTile;

import models.state.State;
import models.state.StateName;

public class IslandSearchedTile extends State {
    public IslandSearchedTile() {
        super(StateName.ISLANDSEARCHED);
    }

    @Override
    public State onHit() {
        return this;
    }
}
