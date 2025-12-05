package views;

import models.grid.Grid;

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
        mainPanel.add(new GridPanel(grid));
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