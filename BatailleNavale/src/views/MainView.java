package views;

import controllers.WeaponController;
import models.game.GameObserver;
import models.game.GameState;
import models.player.Attack;
import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements GameObserver {

    private final JPanel _mainPanel;
    private ConfigPanel config;

    public MainView(GridPanel[] g,WeaponPanel wp) {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        _mainPanel = new JPanel(new BorderLayout());
        add(_mainPanel);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(g[0]);
        centerPanel.add(g[1]);

        _mainPanel.add(centerPanel,BorderLayout.CENTER);

        _mainPanel.add(wp,BorderLayout.SOUTH);
    }

    public void setConfig(ConfigPanel configPanel) {
        return;
//        this.config = configPanel;
//        mainPanel.removeAll();
//        mainPanel.add(config, BorderLayout.CENTER);
    }

    public void displayConfig() {
        setVisible(true);
    }

    @Override
    public void updateNewTurn(int turnNumber) {

    }

    @Override
    public void updateCurrentPlayerIndex(int playerIndex) {

    }

    @Override
    public void updateAttackExecuted(Attack attack, Player target) {

    }

    @Override
    public void updateGameState(GameState state) {
        switch (state) {
            case CONFIG -> {
            }
            case PLACEMENT -> {



            }
            case IN_GAME -> {


            }
            case ENDGAME -> {

            }
        }
    }

    @Override
    public void updateGameOver(Player winner) {
        _mainPanel.removeAll();
        _mainPanel.updateUI();
        _mainPanel.add(new EndScreenPanel(winner),BorderLayout.CENTER);
    }
}