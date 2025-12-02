package views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel mainPanel;
    private ConfigPanel config;

    public MainView() {
        super("Bato jeux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }

    public void setConfig(ConfigPanel configPanel) {
        this.config = configPanel;
        mainPanel.removeAll();
        mainPanel.add(config, BorderLayout.CENTER);
    }

    public void displayConfig() {
        setVisible(true);
    }
}