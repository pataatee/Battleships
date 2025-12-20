package views;

import models.game.GameObserver;
import models.game.GameState;
import models.player.Attack;
import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements GameObserver {

    private final JPanel _mainPanel;
    private ConfigPanel config;
    private PlayerPanel playerPanel1;
    private PlayerPanel playerPanel2;
    private LogsPanel _pnlLogs;

    public MainView(PlayerPanel playerPanel1, PlayerPanel playerPanel2, LogsPanel logPan) {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(854, 480);
        setLocationRelativeTo(null);

        this.playerPanel1 = playerPanel1;
        this.playerPanel2 = playerPanel2;

        _mainPanel = new JPanel(new BorderLayout());
        add(_mainPanel);

        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(playerPanel1);
        centerPanel.add(logPan);
        centerPanel.add(playerPanel2);

        _mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public void setConfig(ConfigPanel configPanel) {
        this.config = configPanel;
        // _mainPanel.removeAll();
        // _mainPanel.add(config, BorderLayout.CENTER);
        // _mainPanel.revalidate();
        // _mainPanel.repaint();
    }

    public void displayConfig() {
        setVisible(true);
    }

    @Override
    public void updateNewTurn(int turnNumber) {
        System.out.println("New turn: " + turnNumber);

    }

    @Override
    public void updateCurrentPlayerIndex(int playerIndex) {
        System.out.println("Current player index: " + playerIndex);
//        if(playerIndex == 0) {
//            playerPanel1.setBackground(Color.GREEN);
//            playerPanel1.updateUI();
//            playerPanel2.updateUI();
//        }
//        if(playerIndex == 1) {
//            playerPanel2.setBackground(Color.GREEN);
//            playerPanel1.updateUI();
//            playerPanel2.updateUI();
//        }

    }

    @Override
    public void updateAttackExecuted(Attack attack, Player target) {
        System.out.println("Attack executed on player: " + target);
    }

    @Override
    public void updateGameState(GameState state) {
        switch (state) {
            case CONFIG -> {
                System.out.println("Game state: CONFIG");
            }
            case PLACEMENT -> {
                System.out.println("Game state: PLACEMENT");
            }
            case IN_GAME -> {
                System.out.println("Game state: IN_GAME");
            }
            case ENDGAME -> {
                System.out.println("Game state: ENDGAME");
            }
        }
    }

    @Override
    public void updateGameOver(Player winner) {
        _mainPanel.removeAll();
        _mainPanel.add(new EndScreenPanel(winner), BorderLayout.CENTER);
        _mainPanel.revalidate();
        _mainPanel.repaint();
    }
}