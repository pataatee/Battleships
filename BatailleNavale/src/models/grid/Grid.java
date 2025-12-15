package models.grid;

import models.game.placement.Orientation;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.player.ShotResult;
import models.player.ShotResultType;
import models.weapon.Weapon;
import models.weapon.WeaponType;

import java.util.ArrayList;

public class Grid{

    private int _size;
    private Tile[][] _tilesMap;
    private ArrayList<GridObserver> _observer;

    public Grid(int size){
        _observer = new ArrayList<GridObserver>();
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
            notifyObserver(x,y,_tilesMap[x][y].getStateName());
        }
    }

    public ShotResult hitTile(int x, int y){
        if(x<0||x>=_size||y<0||y>=_size){
            return new ShotResult(x,y,ShotResultType.MISS);
        }
        ShotResult res =  _tilesMap[x][y].onHit(x,y);
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
            Boat boat = (Boat)object;
            for(int[] position :positions){
                changeStateOfTile(position[0],position[1],TileState.BOAT);
                _tilesMap[position[0]][position[1]].setObject(object);
                boat.addPosition(new int[]{position[0], position[1]});
            }
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

    public void setUpIsland(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                _tilesMap[i+3][j+3]= new IslandTile();
            }
        }
    }



    public void addWeaponToIslandTile(int x , int y, WeaponType wp){
        if(_tilesMap[x][y].getStateName() == TileState.ISLAND){
            ((IslandTile)_tilesMap[x][y]).addWeapon(wp);
            System.out.println(wp+"ajouté!");
        }
    }

    public WeaponType getWeaponFromIslandTile(int x, int y){
        if(_tilesMap[x][y].getStateName() == TileState.ISLANDHIT){
            return ((IslandTile)_tilesMap[x][y]).getWeapon();
        }
        return null;
    }



    public TileState getTileTileState(int x,int y) {
        return _tilesMap[x][y].getStateName();
    }

}