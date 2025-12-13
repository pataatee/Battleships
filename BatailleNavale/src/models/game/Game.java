package models.game;

import models.player.*;

import java.util.ArrayList;
import javax.swing.Timer;

import static models.player.PlayerType.*;

public class Game {
    public static final int NB_PLAYER = 2;

    private Player[] _players;
    private int _currentPlayerIndex;
    private int _currentTurn;
    private GameState _gameState;
    private GameMode _gameMode;
    private ArrayList<GameObserver> _observers;

    public Game(Player player1, Player player2) {
        _players = new Player[]{player1, player2};
        _currentPlayerIndex = 0;
        _currentTurn = 0;
        _gameState = GameState.CONFIG;
        _gameMode = GameMode.NORMAL;
        _observers = new ArrayList<GameObserver>();
    }

    public void setUpGameMode(GameMode gameMode) {
        _gameMode = gameMode;
        if(_gameMode == GameMode.ISLAND) {
            _players[0].setUpIsland();
            _players[1].setUpIsland();
        }
    }

    public void startGame() {
        _gameState = GameState.IN_GAME;
        notifyGameStateChanged();
    }

    public void executeHumanAttack(int x, int y) {
        Player attacker = _players[_currentPlayerIndex];
        if (!isHumanTurn()){
            return;
        }
        if (_gameState != GameState.IN_GAME) return;
        Attack attack = attacker.createAttack(x, y);
        Player opponent = getOpponent();
        ShotResult[] res = opponent.getAttacked(attack);
        this.handelShotResult(res);

        notifyAttackExecuted(attack, opponent);

        if (!opponent.isAlive()) {
            endGame(attacker);
            return;
        }
        nextTurn();
        if (_players[_currentPlayerIndex].getType() == AI) {
            AITurnDelayer();
        }
    }

    private void executeAITurn() {
        AIPlayer aiPlayer = (AIPlayer) _players[_currentPlayerIndex];

        Attack attack = aiPlayer.generateAttack();
        Player opponent = getOpponent();
        ShotResult[] res = opponent.getAttacked(attack);
        this.handelShotResult(res);

        notifyAttackExecuted(attack, opponent);

        if (!opponent.isAlive()) {
            endGame(aiPlayer);
            return;
        }
        nextTurn();
    }

    private void AITurnDelayer() {
        Timer timer = new Timer(0, e -> executeAITurn());
        timer.setRepeats(false);
        timer.start();
    }

    public void nextTurn() {
        _currentTurn++;
        _currentPlayerIndex = (_currentPlayerIndex + 1) % NB_PLAYER;
        notifyNextTurn();
        notifyNextPlayer();
    }

    private Player getOpponent() {
        return _players[(_currentPlayerIndex + 1) % NB_PLAYER];
    }

    private void endGame(Player winner) {
        _gameState = GameState.ENDGAME;
        notifyGameOver(winner);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
    }

    public Player getCurrentPlayer() {
        return _players[_currentPlayerIndex];
    }

    public boolean isHumanTurn() {
        return _players[_currentPlayerIndex].getType() == HUMAN;
    }

    public GameMode getGameMode() {
        return _gameMode;
    }

    public int getCurrentTurn() {
        return _currentTurn;
    }

    public int getCurrentPlayerIndex() {
        return _currentPlayerIndex;
    }

    public GameState getGameState() {
        return _gameState;
    }

    public void addObserver(GameObserver observer) {
        _observers.add(observer);
    }

    public void handelShotResult(ShotResult[] resultTypes){
        for(ShotResult res : resultTypes){
            switch (res.get_type()) {
                case MISS -> {
                    System.out.println("Add to log Miss");
                }
                case HIT -> {
                    System.out.println("Add to log Hit");
                }
                case SUNK -> {
                    System.out.println("Add to log Sunk");
                }
                case TORNAD -> {
                    System.out.println("Add to log Tornadoed");
                }
                case BLACKHOLE -> {
                    System.out.println("Add to log BlackHole");
                }
                case ISLANDHIT -> {
                    System.out.println("Add to log IslandHit");
                }
            }
        }
    }





    private void notifyNextTurn() {
        for (GameObserver ob : _observers) {
            ob.updateNewTurn(_currentTurn);
        }
    }

    private void notifyNextPlayer() {
        for (GameObserver ob : _observers) {
            ob.updateCurrentPlayerIndex(_currentPlayerIndex);
        }
    }

    private void notifyAttackExecuted(Attack attack, Player target) {
        for (GameObserver ob : _observers) {
            ob.updateAttackExecuted(attack, target);
        }
    }

    private void notifyGameStateChanged() {
        for (GameObserver ob : _observers) {
            ob.updateGameState(_gameState);
        }
    }

    private void notifyGameOver(Player winner) {
        for (GameObserver ob : _observers) {
            ob.updateGameOver(winner);
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb");
        }
    }
}