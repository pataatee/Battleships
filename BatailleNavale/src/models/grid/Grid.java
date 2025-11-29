package models.grid;


import java.util.ArrayList;

public class Grid {

    private int _size;
    private Tile[][] _tilesMap;
    private ArrayList<GridObserver> _observer;

    public Grid(int size){
        _observer = new ArrayList<GridObserver>();
        _size = size;
        generateGrid();

    }

    /**
     * Set the grid size and regenerate it
     * @param size
     */
    public void setSize(int size){
        _size = size;
        generateGrid();
    }


    /**
     * Generate the grid with size: _size
     */
    public void generateGrid(){
        _tilesMap = new Tile[_size][_size];

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                _tilesMap[i][j] = new SeaTile();
            }
        }
    }

    /**
     * Change the TileState of the Tile
     * @param x position X of  the tile
     * @param y position Y of the tile
     * @param newState new state of the tile
     */
    public void changeStateOfTile(int x,int y , TileState newState){
        _tilesMap[x][y].setState(newState);
    }


    /**
     * Trigger the event of hit on a tile
     * @param x
     * @param y
     */
    public void hitTile(int x,int y){
        _tilesMap[x][y].onHit();
    }


    /**
     * Check if the tile x,y is free (TileState = Empty)
     * @param x
     * @param y
     * @return false if tile is not free or if the asked cord are of grid
     */
    public boolean isTileFree(int x ,int y){
        if(x>=0 && x<_size && y>=0 && y<_size) {
            return _tilesMap[x][y].isFree();
        }
        return false;
    }

    /**
     * Subscribe a new observer
     * @param ob the new subscriber
     */
    public void addObserver(GridObserver ob){
        _observer.add(ob);
    }


    public void notifyObserver(Tile tile){
        for(GridObserver ob : _observer){
            continue;//to do later
        }
    }

}
