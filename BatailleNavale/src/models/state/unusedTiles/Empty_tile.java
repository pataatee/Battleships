package models.state.unusedTiles;

import models.state.State;
import models.state.StateName;
import models.state.usedTile.Miss_tile;

public class Empty_tile extends State {

    public Empty_tile(){
        super(StateName.EMPTY);
    }

    @Override
    public State onHit() {
        return  new Miss_tile();
    }
}
