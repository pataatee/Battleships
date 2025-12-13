package models.placeable.trap;

import models.grid.Tile;
import models.player.ShotResult;
import models.player.ShotResultType;

public class Tornado extends Trap {

    public Tornado(int ownerId) {
        super(ownerId, "Tornado", 1);
    }

    @Override
    public ShotResult onHit(int x ,int y) {
        //TODO: code tornado's effect
        super.setActivated(true);
        return new ShotResult(x,y,ShotResultType.TORNAD);
    }

    public void updateTile(Tile tile) {
        //TODO: complete this method
    }
}
