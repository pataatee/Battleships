package models.grid;

import models.player.ShotResultType;

public class SeaTile extends Tile{

    public SeaTile(){
        super(TileState.EMPTY);
    }
    @Override
    public ShotResultType onHit() {
        switch (this.getStateName()){
            case BOAT -> {
                this.setState(TileState.BOATHIT);
                return getObject().onHit();
            }
            case EMPTY ->{
                this.setState(TileState.MISS);
                getObject().onHit();
            }
            case TRAP -> {
                this.setState(TileState.TRAPHIT);
                getObject().onHit();
            }
        }
        return ShotResultType.MISS;
    }

}
