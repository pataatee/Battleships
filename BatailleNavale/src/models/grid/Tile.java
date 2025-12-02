package models.grid;

import models.placeable.EmptyObject;
import models.placeable.Placeable;

import java.util.ArrayList;


public abstract class Tile {

    private TileState _currentState;
    private Placeable _obj;
    public Tile(TileState def)
    {
        _currentState = def;
        _obj = new EmptyObject();
    }


    /**
     * Change the _currentState of the tile
     * @param state
     */
    public void setState(TileState state)
    {
        this._currentState = state;
    }

    public TileState getStateName(){
        return _currentState;
    }

    /**
     *Check if the tile is empty
     * @return return true if the current TileState is Empty
     */
    public boolean isFree(){
        return _currentState == TileState.EMPTY;
    }

    /**
     * Call the OnHit event of the current TileState
     */
    public abstract void onHit();

    public void setObject(Placeable object){
        _obj = object;
    }

    public Placeable getObject(){
        return  _obj;
    }

}
