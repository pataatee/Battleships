package controllers;

import models.game.Game;
import models.game.GameMode;
import models.game.GameState;
import models.game.placement.ConfigData;

public class GameController {

    private Game _gameModel;
    private ConfigData _configData;

    public GameController(Game toControl, ConfigData cd) {
        _gameModel = toControl;
        _configData = cd;
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

    public void setGameMode(GameMode gameMode) {
        this._gameModel.setUpGameMode(gameMode);
    }

    public void confirmConfig(){
        _gameModel.confirmConfig(_configData.getChosenBoats());
        _gameModel.setUpGameMode(_configData.getGameMode());
    }
}
