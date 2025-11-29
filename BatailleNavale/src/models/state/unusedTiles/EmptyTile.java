package models.state.unusedTiles;

import models.state.State;
import models.state.StateName;
import models.state.usedTile.MissTile;

public class EmptyTile extends State {

    public EmptyTile(){
        super(StateName.EMPTY);
    }

    @Override
    public State onHit() {
        return  new MissTile();
    }
}
