package models.game;

public interface GameObserver {
        public void updateNewTurn(int turn);
        public void updateCurrentPlayerIndex(int player);
}
