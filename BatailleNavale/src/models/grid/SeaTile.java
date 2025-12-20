package models.grid;

import models.player.ShotResult;
import models.player.ShotResultType;

public class SeaTile extends Tile {

    public SeaTile() {
        super(TileState.EMPTY);
    }

    @Override
    public ShotResult onHit(int x, int y) {
        switch (this.getStateName()) {
            case BOAT -> {
                this.setState(TileState.BOATHIT);
                return getObject().onHit(x, y);
            }
            case EMPTY -> {
                this.setState(TileState.MISS);
                getObject().onHit(x, y);
            }
            case TRAP -> {
                this.setState(TileState.TRAPHIT);
                return getObject().onHit(x, y);
            }
        }
        return new ShotResult(x, y, ShotResultType.MISS);
    }

}
