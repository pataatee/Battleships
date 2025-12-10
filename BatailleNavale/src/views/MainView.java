package views;

import controllers.WeaponController;
import models.game.GameObserver;
import models.game.GameState;
import models.grid.Grid;
import models.player.AIPlayer;
import models.player.Attack;
import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements GameObserver {

    private JPanel mainPanel;
    private ConfigPanel config;

    public MainView(GridPanel[] g) {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 900);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(g[0]);
        centerPanel.add(g[1]);

        mainPanel.add(centerPanel,BorderLayout.CENTER);

        mainPanel.add(new WeaponPanel(new WeaponController(new AIPlayer("AI",1,new Grid(10)))),BorderLayout.SOUTH);
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
        mainPanel.removeAll();
        mainPanel.updateUI();
        mainPanel.add(new EndScreenPanel(winner),BorderLayout.CENTER);
    }
}