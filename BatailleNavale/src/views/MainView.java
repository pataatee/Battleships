package views;

import controllers.WeaponController;
import models.grid.Grid;
import models.player.AIPlayer;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

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
}