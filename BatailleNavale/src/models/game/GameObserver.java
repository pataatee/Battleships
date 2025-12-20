package models.game;

import models.player.Attack;
import models.player.Player;

public interface GameObserver {
    void updateNewTurn(int turnNumber);

    void updateCurrentPlayerIndex(int playerIndex);

    void updateAttackExecuted(Attack attack, Player target);

    void updateGameState(GameState state);

    void updateGameOver(Player winner);
}
