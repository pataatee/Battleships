package views;

import controllers.GameController;
import models.game.GameObserver;
import models.game.GameState;
import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame implements GameObserver {

    private final JPanel _mainPanel;
    private ConfigPanel config;
    private PlayerPanel playerPanel1;
    private PlayerPanel playerPanel2;
    private GameController _gameController;
    private LogsPanel _logsPanel;
    private PlacementView _placementView;
    private StatsPanel _statsPanel1;
    private StatsPanel _statsPanel2;

    public MainView(ConfigPanel configPanel, PlacementView PlacementPanel, PlayerPanel playerPanel1, PlayerPanel playerPanel2, LogsPanel logsPanel, StatsPanel stats1, StatsPanel stats2, GameController gameController) {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1240, 480);
        setLocationRelativeTo(null);
        this.config = configPanel;
        this._placementView = PlacementPanel;
        this.playerPanel1 = playerPanel1;
        this.playerPanel2 = playerPanel2;
        this._gameController = gameController;
        _mainPanel = new JPanel(new BorderLayout());
        add(_mainPanel);
        _logsPanel = logsPanel;
        _statsPanel1 = stats1;
        _statsPanel2 = stats2;


        _mainPanel.add(configPanel, BorderLayout.CENTER);
    }

    public void startGame() {
        _mainPanel.removeAll();
        // Conteneur pour joueur 1 : PlayerPanel + StatsPanel
        JPanel playerContainer1 = new JPanel();
        playerContainer1.setLayout(new BoxLayout(playerContainer1, BoxLayout.Y_AXIS));
        playerContainer1.add(playerPanel1);
        playerContainer1.add(_statsPanel1);

        // Conteneur pour joueur 2 : PlayerPanel + StatsPanel
        JPanel playerContainer2 = new JPanel();
        playerContainer2.setLayout(new BoxLayout(playerContainer2, BoxLayout.Y_AXIS));
        playerContainer2.add(playerPanel2);
        playerContainer2.add(_statsPanel2);

        // Centre : player1+stats | logs | player2+stats
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(playerContainer1);
        centerPanel.add(_logsPanel);
        centerPanel.add(playerContainer2);

        _mainPanel.add(centerPanel, BorderLayout.CENTER);
        _mainPanel.revalidate();
        _mainPanel.repaint();
        this.repaint();
    }

    @Override
    public void updateGameState(GameState state) {
        _mainPanel.removeAll();
        switch (state) {
            case CONFIG -> {
                _mainPanel.add(config, BorderLayout.CENTER);
            }
            case PLACEMENT -> {
                _mainPanel.add(_placementView, BorderLayout.CENTER);
                _mainPanel.revalidate();
                _mainPanel.repaint();
                this.repaint();
            }
            case IN_GAME -> {
                this.startGame();
            }
        }
        _mainPanel.revalidate();
        _mainPanel.repaint();
    }

    @Override
    public void updateGameOver(Player winner) {
        _mainPanel.removeAll();
        _mainPanel.add(new EndScreenPanel(winner, _gameController), BorderLayout.CENTER);
        _mainPanel.revalidate();
        _mainPanel.repaint();
    }
}