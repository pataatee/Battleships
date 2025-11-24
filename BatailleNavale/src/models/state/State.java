package models.state;

public abstract class State {
    private final StateName _currentState;

    public State(StateName state){
        _currentState=state;
    }

    /**
     * Return the current state Name of the tile
     * @return the current state
     */
    public StateName getCurrentState(){
        return _currentState;
    }

    /**
     * Able to react when tile is being Hit
     * @return the new tile state after the hit
     */
    public abstract State onHit();


}
