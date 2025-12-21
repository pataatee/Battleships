package controllers;

import models.game.Game;
import models.game.GameMode;
import models.game.GameState;

public class GameController {

    private Game _gameModel;

    public GameController(Game toControl) {
        _gameModel = toControl;
    }

    public void SendAttack(int x, int y) {
        _gameModel.executeHumanAttack(x, y);
    }


    public void setState(GameState gameState) {
     _gameModel.setGameState(gameState);
    }
    public GameMode getGameMode() {
        return _gameModel.getGameMode();
    }
}
