package models.placeable.trap;

import models.grid.Tile;

public class BlackHole extends Trap {

    public BlackHole(int ownerId) {
        super(ownerId, "Black Hole", 1);
    }

    @Override
    public void onHit() {
        //TODO: code BlackHole's effect
        super.setActivated(true);
    }

    public void updateTile(Tile tile) {
        //TODO: complete this
    }
}
