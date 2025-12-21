package models.game;

import models.player.Attack;
import models.player.Player;

public interface GameObserver {
    void updateGameState(GameState state);

    void updateGameOver(Player winner);
}
