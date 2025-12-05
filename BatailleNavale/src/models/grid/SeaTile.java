package models.grid;

public class SeaTile extends Tile{

    public SeaTile(){
        super(TileState.EMPTY);
    }
    @Override
    public void onHit() {
        switch (this.getStateName()){
            case BOAT -> this.setState(TileState.BOATHIT);
            case EMPTY -> this.setState(TileState.MISS);
        }
        getObject().onHit();
    }
}
