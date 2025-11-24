package models.grid;

import models.state.State;
import models.state.StateName;
import models.state.unusedTiles.Empty_tile;

import java.util.ArrayList;


public class Tile {

    private int _x,_y;
    private State _currentState;
    private ArrayList<TileObserver> _observer;
    public Tile(int x, int y)
    {
        _currentState = new Empty_tile();
        _x=x;
        _y=y;
        _observer = new ArrayList<TileObserver>();
    }


    /**
     * Change the _currentState of the tile
     * @param state
     */
    public void setState(State state)
    {
        this._currentState = state;
    }

    public StateName getStateName(){
        return _currentState.getCurrentState();
    }

    /**
     *Check if the tile is empty
     * @return return true if the current State is Empty
     */
    public boolean isFree(){
        return _currentState.getCurrentState() == StateName.EMPTY;
    }

    /**
     * Call the OnHit event of the current State
     */
    public void onHit(){
        _currentState = _currentState.onHit();
        notify_observer();
    }


    /**
     * Notify the observer that the state has change
     * Side note : this function is only call when the state change after an OnHit event
     */
    private void notify_observer(){
        for(TileObserver ob :_observer){
            ob.update_tile(_currentState);
        }
    }


    /**
     * Subscribe a new Observer to the list of observers
     * @param ob
     */
    public void addObserver(TileObserver ob){
        _observer.add(ob);
    }



}
