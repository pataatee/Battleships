package models.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void setUpGameMode() {
        var g = new Game();
        g.setUpGameMode(GameMode.ISLAND);
        assertSame(GameMode.ISLAND, g.getGameMode());
    }

    @Test
    void nextTurn() {
        var g = new Game();
        g.nextTurn();
        assertSame(1, g.getCurrentTurn());
        assertSame(1,g.getCurrentPlayerIndex());
        g.nextTurn();
        assertSame(2, g.getCurrentTurn());
        assertSame(0,g.getCurrentPlayerIndex());
        g.nextTurn();
        assertSame(3, g.getCurrentTurn());
        assertSame(1,g.getCurrentPlayerIndex());

    }

}