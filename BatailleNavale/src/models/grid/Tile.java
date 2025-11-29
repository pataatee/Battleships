package models.grid;

import java.util.ArrayList;


public abstract class Tile {

    private ArrayList<TileObserver> _observer;
    private TileState _currentState;
    public Tile(TileState def)
    {
        _observer = new ArrayList<TileObserver>();
        _currentState = def;
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
