package models.placeable;

import models.player.ShotResultType;

public class EmptyObject extends Placeable{
    public EmptyObject() {
        super("Empty Object", 1,PlaceableType.EMPTY);
    }

    @Override
    public ShotResultType onHit() {
        return ShotResultType.MISS;
        //TODO: dunno if i have to write something here but feels weird to leave it empty
    }
}
