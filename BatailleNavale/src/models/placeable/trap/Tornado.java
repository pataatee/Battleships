package models.placeable.trap;

import models.grid.Tile;
import models.player.ShotResult;
import models.player.ShotResultType;

public class Tornado extends Trap {

    public Tornado() {
        super("Tornado", 1);
    }

    @Override
    public ShotResult onHit(int x, int y) {
        super.setActivated(true);
        return new ShotResult(x, y, ShotResultType.TORNAD);
    }

}
