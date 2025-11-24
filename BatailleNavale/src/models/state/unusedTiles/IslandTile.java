package models.state.unusedTiles;

import models.state.State;
import models.state.StateName;
import models.state.usedTile.IslandSearchedTile;

public class IslandTile extends State {
    public IslandTile() {
        super(StateName.ISLAND);
    }

    @Override
    public State onHit() {
        return new IslandSearchedTile();
    }
}
