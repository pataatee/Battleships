package controllers;

import models.game.Game;

public class GameController {

    private Game _gameModel;

    public GameController(Game toControl) {
        _gameModel = toControl;
    }

    public void SendAttack(int x, int y) {
        _gameModel.executeHumanAttack(x, y);
    }


}
