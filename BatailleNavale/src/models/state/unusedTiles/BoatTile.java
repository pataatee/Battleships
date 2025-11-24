package models.state.unusedTiles;

import models.state.State;
import models.state.StateName;
import models.state.usedTile.BoatHitTile;

public class BoatTile extends State {
    public BoatTile() {
        super(StateName.BOAT);
    }

    @Override
    public State onHit() {
        return new BoatHitTile();
    }
}
