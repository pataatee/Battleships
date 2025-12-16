package models.placeable.trap;

import models.grid.Tile;
import models.player.ShotResult;
import models.player.ShotResultType;

public class BlackHole extends Trap {

    public BlackHole(int ownerId) {
        super(ownerId, "Black Hole", 1);
    }

    @Override
    public ShotResult onHit(int x,int y) {
        //TODO: code BlackHole's effect
        super.setActivated(true);
        return new ShotResult(x,y,ShotResultType.BLACKHOLE);
    }

    public void updateTile(Tile tile) {
        //TODO: complete this
    }
}
