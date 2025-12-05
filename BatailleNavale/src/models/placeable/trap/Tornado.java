package models.placeable.trap;

import models.grid.Tile;

public class Tornado extends Trap {

    public Tornado(int ownerId) {
        super(ownerId, "Tornado", 1);
    }

    @Override
    public void onHit() {
        //TODO: code tornado's effect
        super.setActivated(true);
    }

    public void updateTile(Tile tile) {
        //TODO: complete this method
    }
}
