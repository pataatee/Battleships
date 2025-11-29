package models.state.unusedTiles;

import models.state.State;
import models.state.usedTile.TrapUsedTile;

import static models.state.StateName.TRAP;

public class TrapTile extends State {
    public TrapTile(){
        super(TRAP);
    }
    @Override
    public State onHit() {
        return new TrapUsedTile();
    }
}
