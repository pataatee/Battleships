package models.game;


import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int nbPlayer =2;
    private int _currentPlayerIndex;
    private int _currentTurn;
    private GameState _gameState;
    private GameMode _gameMode;
    private ArrayList<GameObserver> _observer;

    public Game(){
        _currentPlayerIndex=0;
        _currentTurn=0;
        _gameState = GameState.CONFIG;
        _gameMode = GameMode.NORMAL;
        _observer = new ArrayList<GameObserver>();
    }

    public void setUpGame(GameMode gameMode){
        _gameMode =gameMode;
    }

    public void nextTurn(){
        _currentTurn++;
        _currentPlayerIndex=(_currentPlayerIndex+1)%nbPlayer;
    }

    public void notifyNextTurn(){
        for(GameObserver ob : _observer){
            ob.updateNewTurn(_currentTurn);
        }
    }


    public void notifyNextPlayer(){
        for(GameObserver ob : _observer){
            ob.updateCurrentPlayerIndex(_currentPlayerIndex);
        }
    }




}
