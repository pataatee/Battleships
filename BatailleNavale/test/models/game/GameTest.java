package models.game;

import models.grid.Grid;
import models.player.AIPlayer;
import models.player.Attack;
import models.player.HumanPlayer;
import models.player.Player;
import models.weapon.Missile;
import models.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Grid grid1;
    private Grid grid2;
    private HumanPlayer humanPlayer;
    private AIPlayer aiPlayer;
    private Game game;

    @BeforeEach
    void setUp() {
        grid1 = new Grid(10);
        grid2 = new Grid(10);
        humanPlayer = new HumanPlayer("Player1", 1, grid1);
        aiPlayer = new AIPlayer("AI", 2, grid2);
        game = new Game(humanPlayer, aiPlayer);
    }


    @Test
    void testInitialState() {
        assertEquals(GameState.CONFIG, game.getGameState());
        assertEquals(0, game.getCurrentTurn());
        assertEquals(0, game.getCurrentPlayerIndex());
        assertEquals(GameMode.NORMAL, game.getGameMode());
    }

    @Test
    void testSetUpGameMode() {
        game.setUpGameMode(GameMode.ISLAND);
        assertEquals(GameMode.ISLAND, game.getGameMode());

        game.setUpGameMode(GameMode.NORMAL);
        assertEquals(GameMode.NORMAL, game.getGameMode());
    }

    @Test
    void testStartGame() {
        game.startGame();
        assertEquals(GameState.IN_GAME, game.getGameState());
    }


    @Test
    void testNextTurnIncrementsCorrectly() {
        game.nextTurn();
        assertEquals(1, game.getCurrentTurn());
        assertEquals(1, game.getCurrentPlayerIndex());

        game.nextTurn();
        assertEquals(2, game.getCurrentTurn());
        assertEquals(0, game.getCurrentPlayerIndex());

        game.nextTurn();
        assertEquals(3, game.getCurrentTurn());
        assertEquals(1, game.getCurrentPlayerIndex());
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(humanPlayer, game.getCurrentPlayer());

        game.nextTurn();
        assertEquals(aiPlayer, game.getCurrentPlayer());

        game.nextTurn();
        assertEquals(humanPlayer, game.getCurrentPlayer());
    }

    @Test
    void testIsHumanTurn() {
        assertTrue(game.isHumanTurn());

        game.nextTurn();
        assertFalse(game.isHumanTurn());

        game.nextTurn();
        assertTrue(game.isHumanTurn());
    }


    @Test
    void testHumanAttackWhenNotHumanTurn() {
        game.nextTurn();
        int turnBefore = game.getCurrentTurn();

        game.executeHumanAttack(5, 5);

        assertEquals(turnBefore, game.getCurrentTurn());
        assertEquals(1, game.getCurrentPlayerIndex());
    }

    @Test
    void testHumanAttackChangesToAITurn() {
        game.startGame();

        game.executeHumanAttack(3, 4);

        assertEquals(1, game.getCurrentTurn());
        assertEquals(1, game.getCurrentPlayerIndex());
        assertFalse(game.isHumanTurn());
    }


    @Test
    void testObserverNotifications() {
        TestGameObserver observer = new TestGameObserver();
        game.addObserver(observer);

        game.startGame();
        assertTrue(observer.gameStateChanged);

        game.nextTurn();
        assertTrue(observer.turnChanged);
        assertTrue(observer.playerChanged);
    }

    @Test
    void testAttackNotifiesObservers() {
        TestGameObserver observer = new TestGameObserver();
        game.addObserver(observer);
        game.startGame();

        game.executeHumanAttack(5, 5);

        assertTrue(observer.attackExecuted);
        assertNotNull(observer.lastAttack);
        assertNotNull(observer.lastTarget);
    }


    @Test
    void testFullGameFlow() {
        TestGameObserver observer = new TestGameObserver();
        game.addObserver(observer);

        game.startGame();
        assertEquals(GameState.IN_GAME, game.getGameState());
        assertTrue(observer.gameStateChanged);

        assertTrue(game.isHumanTurn());
        game.executeHumanAttack(5, 5);
        assertEquals(1, game.getCurrentTurn());
        assertTrue(observer.attackExecuted);

    }

    @Test
    void testMultipleConsecutiveTurns() {
        game.startGame();

        for (int i = 0; i < 10; i++) {
            if (game.isHumanTurn()) {
                game.executeHumanAttack(i % 10, i % 10);
            }
        }

        assertTrue(game.getCurrentTurn() > 0);
    }

    @Test
    void testWeaponChangeDoesNotAffectTurn() {
        game.startGame();
        Weapon newWeapon = new Missile();

        game.getCurrentPlayer().setWeapon(newWeapon);

        assertEquals(0, game.getCurrentTurn());
        assertEquals(0, game.getCurrentPlayerIndex());
    }


    private static class TestGameObserver implements GameObserver {
        boolean turnChanged = false;
        boolean playerChanged = false;
        boolean attackExecuted = false;
        boolean gameStateChanged = false;
        boolean gameOver = false;
        Attack lastAttack = null;
        Player lastTarget = null;
        Player winner = null;

        @Override
        public void updateNewTurn(int turnNumber) {
            turnChanged = true;
        }

        @Override
        public void updateCurrentPlayerIndex(int playerIndex) {
            playerChanged = true;
        }

        @Override
        public void updateAttackExecuted(Attack attack, Player target) {
            attackExecuted = true;
            lastAttack = attack;
            lastTarget = target;
        }

        @Override
        public void updateGameState(GameState state) {
            gameStateChanged = true;
        }

        @Override
        public void updateGameOver(Player winnerPlayer) {
            gameOver = true;
            winner = winnerPlayer;
        }
    }
}