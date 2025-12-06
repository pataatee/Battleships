package models.grid;

import models.game.placement.Orientation;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatsObserver;
import models.player.ShotResultType;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid implements BoatsObserver {

    private int _size;
    private Tile[][] _tilesMap;
    private ArrayList<GridObserver> _observer;
    private HashMap<Boat, ArrayList<int[]>> _boatPositions;

    public Grid(int size){
        _observer = new ArrayList<GridObserver>();
        _boatPositions = new HashMap<Boat, ArrayList<int[]>>();
        _size = size;
        generateGrid();
    }

    public void setSize(int size){
        _size = size;
        generateGrid();
    }

    public void generateGrid(){
        _tilesMap = new Tile[_size][_size];

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                _tilesMap[i][j] = new SeaTile();
            }
        }
    }

    public void changeStateOfTile(int x,int y , TileState newState){
        if(x>=0 && x<_size && y>=0 && y<_size) {
            _tilesMap[x][y].setState(newState);
        }
    }

    public ShotResultType hitTile(int x, int y){
        ShotResultType res =  _tilesMap[x][y].onHit();
        notifyObserver(x,y,_tilesMap[x][y].getStateName());
        return res;
    }

    public boolean isTileFree(int x ,int y){
        if(x>=0 && x<_size && y>=0 && y<_size) {
            return _tilesMap[x][y].isFree();
        }
        return false;
    }

    public void addObserver(GridObserver ob){
        _observer.add(ob);
    }

    public void notifyObserver(int x, int y,TileState state){
        for(GridObserver ob : _observer){
            ob.updateTileState(x,y,state);
        }
    }

    public Boolean placeObject(Placeable object, int x, int y, Orientation orientation) {
        int[][] positions = object.skibidiRizzler(x,y,orientation);

        for(int[] position :positions){
            if(!isTileFree(position[0],position[1])){
                return false;
            }
        }

        if(object.getPlaceableType()== PlaceableType.BOAT){
            ArrayList<int[]> boatPos = new ArrayList<int[]>();
            Boat boat = (Boat)object;
            boat.addObserver(this);

            for(int[] position :positions){
                changeStateOfTile(position[0],position[1],TileState.BOAT);
                _tilesMap[position[0]][position[1]].setObject(object);
                boatPos.add(new int[]{position[0], position[1]});
            }
            _boatPositions.put(boat, boatPos);
        }
        else if(object.getPlaceableType()== PlaceableType.TRAP){
            for(int[] position :positions){
                changeStateOfTile(position[0],position[1],TileState.TRAP);
                _tilesMap[position[0]][position[1]].setObject(object);
            }
        }

        return true;
    }

    public int getSize() {
        return this._size;
    }

    public TileState getTileTileState(int x,int y) {
        return _tilesMap[x][y].getStateName();
    }

    @Override
    public void reactOnDeath(Boat boat) {
        ArrayList<int[]> positions = _boatPositions.get(boat);
        if(positions != null){
            for(int[] pos : positions){
                changeStateOfTile(pos[0], pos[1], TileState.BOATDEAD);
                notifyObserver(pos[0], pos[1], TileState.BOATDEAD);
            }
        }
    }
}