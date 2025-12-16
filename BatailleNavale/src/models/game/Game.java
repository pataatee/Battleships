package models.game;

import models.player.AIPlayer;
import models.player.Attack;
import models.player.Player;
import models.player.ShotResult;

import javax.swing.*;
import java.util.ArrayList;

import static models.player.PlayerType.AI;
import static models.player.PlayerType.HUMAN;

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
        _observers = new ArrayList<>();
    }

    public void setUpGameMode(GameMode gameMode) {
        _gameMode = gameMode;
        if (_gameMode == GameMode.ISLAND) {
            _players[0].setUpIsland();
            _players[1].setUpIsland();
        }
    }

    public void startGame() {
        _gameState = GameState.IN_GAME;
        notifyGameStateChanged();
    }

    private void processAttack(Attack attack) {
        Player attacker = getCurrentPlayer();
        Player opponent = getOpponent();

        ShotResult[] results = opponent.getAttacked(attack);
        attacker.handelShotResult(results);

        notifyAttackExecuted(attack, opponent);

        if (!opponent.isAlive()) {
            endGame(attacker);
            return;
        }

        nextTurn();

        if (isAITurn()) {
            scheduleAITurn();
        }
    }

    public void executeHumanAttack(int x, int y) {
        if (!isHumanTurn() || _gameState != GameState.IN_GAME) {
            return;
        }

        Player attacker = getCurrentPlayer();
        Attack attack = attacker.createAttack(x, y);
        processAttack(attack);
    }

    private void executeAITurn() {
        AIPlayer aiPlayer = (AIPlayer) getCurrentPlayer();
        Attack attack = aiPlayer.generateAttack();
        processAttack(attack);
    }

    private void scheduleAITurn() {
        Timer timer = new Timer(500, e -> executeAITurn()); // 500ms de délai
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
    }


    public Player getCurrentPlayer() {
        return _players[_currentPlayerIndex];
    }

    public boolean isHumanTurn() {
        return getCurrentPlayer().getType() == HUMAN;
    }

    public boolean isAITurn() {
        return getCurrentPlayer().getType() == AI;
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

    private void notifyGameOver (Player winner){
        for (GameObserver ob : _observers) {
            ob.updateGameOver(winner);
        }
    }
}
