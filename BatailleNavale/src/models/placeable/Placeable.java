package models.placeable;

import models.game.placement.Orientation;
import models.player.ShotResult;
import models.player.ShotResultType;

public abstract class Placeable {
    private int _size;
    private String _name;
    private PlaceableType _type;

    public Placeable(String name, int size, PlaceableType type) {
        this._name=name;
        this._size=size;
        this._type = type;
    }

    public abstract ShotResult onHit(int x ,int y);

    public int getSize() {
        return this._size;
    }

    public String getName() {
        return this._name;
    }

    public PlaceableType getPlaceableType() {
        return this._type;
    }


    public int[][] skibidiRizzler(int x, int y, Orientation orientation){

        int[][] tiles = new int[_size][2];
        for(int i = 0;i<_size;i++){
            int newX =x;
            int newY =y;
            if (orientation == Orientation.HORIZONTAL){
                newX =i+x;
            }
            if (orientation == Orientation.VERTICAL){
                newY =i+y;
            }
            tiles[i] = new int[]{newX,newY};
        }
        return  tiles;
    }

    public abstract void resetPositions();



}
