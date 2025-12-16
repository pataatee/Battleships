package views;

import controllers.GameController;
import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private final Grid grid;
    private final boolean isPlayerView;
    private final GameController controller;

    private CellPanel cellPanel;
    private ButtonGridPanel buttonPanel;

    public GridPanel(GameController gc, Grid grid, boolean isPlayerView) {
        this.grid = grid;
        this.isPlayerView = isPlayerView;
        this.controller = gc;

        int size = grid.getSize();

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createTopLabels(size), BorderLayout.NORTH);
        add(createLeftLabels(size), BorderLayout.WEST);
        add(createGridCenter(size), BorderLayout.CENTER);
    }

    private JPanel createTopLabels(int size) {
        JPanel panel = new JPanel(new GridLayout(1, size));
        for (int i = 0; i < size; i++) {
            panel.add(createLabel(String.valueOf((char) ('A' + i))));
        }
        return panel;
    }

    private JPanel createLeftLabels(int size) {
        JPanel panel = new JPanel(new GridLayout(size, 1));
        panel.setPreferredSize(new Dimension(30, 0));
        for (int i = 0; i < size; i++) {
            panel.add(createLabel(String.valueOf(i + 1)));
        }
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JComponent createGridCenter(int size) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1)
        ));

        cellPanel = new CellPanel(grid, isPlayerView);
        grid.addObserver(cellPanel);

        if (isPlayerView) {
            container.add(cellPanel, BorderLayout.CENTER);
        } else {
            buttonPanel = new ButtonGridPanel(controller, size);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setLayout(new OverlayLayout(layeredPane));

            JPanel gridWrapper = new JPanel(new BorderLayout());
            gridWrapper.add(cellPanel, BorderLayout.CENTER);

            layeredPane.add(buttonPanel);
            layeredPane.add(gridWrapper);

            container.add(layeredPane, BorderLayout.CENTER);
        }

        return container;
    }
}
