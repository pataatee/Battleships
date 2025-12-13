package models.placeable;

import models.player.ShotResult;
import models.player.ShotResultType;

public class EmptyObject extends Placeable{
    public EmptyObject() {
        super("Empty Object", 1,PlaceableType.EMPTY);
    }

    @Override
    public ShotResult onHit(int x , int y) {
        return new ShotResult(x,y,ShotResultType.MISS);
        //TODO: dunno if i have to write something here but feels weird to leave it empty
    }
}
