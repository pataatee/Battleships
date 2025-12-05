package models.grid;


public interface GridObserver {

    /**
     * @param x the x cord of the tile that change
     * @param y the y cord of the tile that change
     * @param state the new state of the tile
     */
    public void updateTileState(int x , int y ,TileState state);
}
