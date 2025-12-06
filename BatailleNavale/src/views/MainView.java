package views;

import controllers.WeaponController;
import models.grid.Grid;
import models.player.AIPlayer;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel mainPanel;
    private ConfigPanel config;

    public MainView(Grid grid) {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
//        mainPanel.add(new GridPanel(grid));
        mainPanel.add(new WeaponPanel(new WeaponController(new AIPlayer("AI",1,new Grid(10)))));
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