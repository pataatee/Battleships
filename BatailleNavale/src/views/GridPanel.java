package views;

import controllers.GameController;
import models.grid.Grid;
import models.grid.GridObserver;
import models.grid.TileState;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel implements GridObserver {

    private Grid _grid;
    private JPanel gridContainer;
    private JPanel buttonOverlay;
    private JLayeredPane layeredPane;
    private boolean isPlayerView;
    private GameController _controller;
    private JPanel topLabels;
    private JPanel leftLabels;

    public GridPanel(GameController gc, Grid grid, boolean isPlayerView){
        this._grid = grid;
        this.isPlayerView = isPlayerView;
        this._controller = gc;

        int size = grid.getSize();

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel pour les index de colonnes (en haut)
        topLabels = new JPanel(new GridLayout(1, size, 0, 0));
        topLabels.setBackground(getBackground());
        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel(String.valueOf((char)('A' + i)), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            topLabels.add(label);
        }

        // Panel pour les index de lignes (à gauche)
        leftLabels = new JPanel(new GridLayout(size, 1, 0, 0));
        leftLabels.setBackground(getBackground());
        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            leftLabels.add(label);
        }

        // Container avec la grille carrée
        SquarePanel squareContainer = new SquarePanel();
        squareContainer.setLayout(new BorderLayout());
        squareContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1)
        ));

        initGrid(size);

        if (!isPlayerView) {
            initAttackButtons(size);
            squareContainer.add(layeredPane, BorderLayout.CENTER);
        } else {
            squareContainer.add(gridContainer, BorderLayout.CENTER);
        }

        // Panel central qui contient la grille + les labels
        JPanel centerPanel = new JPanel(new BorderLayout(0, 0));

        // Top panel avec espacement à gauche
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(Box.createHorizontalStrut(30), BorderLayout.WEST);
        topPanel.add(topLabels, BorderLayout.CENTER);

        // Left panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(leftLabels, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(30, 0));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(leftPanel, BorderLayout.WEST);
        centerPanel.add(squareContainer, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Listener pour synchroniser les tailles
        squareContainer.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                syncLabelSizes(squareContainer.getWidth(), squareContainer.getHeight());
            }
        });
    }

    private void syncLabelSizes(int width, int height) {
        if (topLabels != null) {
            topLabels.setPreferredSize(new Dimension(width, 25));
            topLabels.revalidate();
        }
        if (leftLabels != null) {
            leftLabels.setPreferredSize(new Dimension(30, height));
            leftLabels.revalidate();
        }
    }

    private void initGrid(int size){
        gridContainer = new JPanel(new GridLayout(size, size, 0, 0));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = new JLabel();
                cell.setBackground(getTileColor(i, j));
                cell.setOpaque(true);
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                gridContainer.add(cell);
            }
        }
    }

    private void initAttackButtons(int size){
        buttonOverlay = new JPanel(new GridLayout(size, size, 0, 0));
        buttonOverlay.setOpaque(false);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int x = i;
                final int y = j;

                JButton attackButton = new JButton();
                attackButton.setOpaque(false);
                attackButton.setContentAreaFilled(false);
                attackButton.setBorderPainted(false);
                attackButton.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                attackButton.addActionListener(evt->onCellAttacked(x,y));

                buttonOverlay.add(attackButton);
            }
        }

        // Utiliser un JLayeredPane pour superposer les boutons
        layeredPane = new JLayeredPane();

        // Wrapper avec BorderLayout pour le gridContainer
        JPanel gridWrapper = new JPanel(new BorderLayout());
        gridWrapper.add(gridContainer, BorderLayout.CENTER);

        layeredPane.setLayout(new OverlayLayout(layeredPane));
        layeredPane.add(buttonOverlay);
        layeredPane.add(gridWrapper);
    }

    private Color getTileColor(int x, int y){
        TileState state = _grid.getTileTileState(x, y);

        if (isPlayerView) {
            switch (state){
                case EMPTY -> { return Color.CYAN ;}
                case MISS -> { return Color.WHITE; }
                case BOAT -> { return Color.DARK_GRAY; }
                case BOATHIT -> { return Color.RED; }
                case BOATDEAD -> { return Color.BLACK; }
                case TRAP -> { return Color.ORANGE; }
                case TRAPHIT -> { return Color.YELLOW ;}
                case SEARCHED -> { return Color.DARK_GRAY ;}
                case NOTSEARCHED -> { return Color.MAGENTA; }
                case ISLAND -> {return  Color.yellow;}
                case ISLANDHIT -> {return  Color.BLUE;}
            }
        }
        else {
            switch (state){
                case MISS -> { return Color.WHITE; }
                case BOATHIT -> { return Color.RED; }
                case TRAPHIT -> { return Color.ORANGE; }
                case BOATDEAD -> { return Color.BLACK; }
                case ISLAND -> {return  Color.yellow;}
                case ISLANDHIT -> {return  Color.BLUE;}
                default -> { return Color.CYAN; }
            }
        }

        return Color.PINK;
    }

    private void onCellAttacked(int x, int y) {
        System.out.println("Attaque sur la case [" + x + ", " + y + "]");
        _controller.SendAttack(x,y);
    }

    @Override
    public void updateTileState(int x, int y, TileState state) {
        int size = _grid.getSize();
        int index = x * size + y;
        Component comp = gridContainer.getComponent(index);
        JLabel cell = (JLabel) comp;
        cell.setBackground(getTileColor(x, y));
        cell.repaint();
        gridContainer.revalidate();
        gridContainer.repaint();
    }


    // Classe interne pour maintenir un aspect ratio carré
    private class SquarePanel extends JPanel {
        @Override
        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            Container parent = getParent();
            if (parent != null) {
                Dimension parentSize = parent.getSize();
                int size = Math.min(parentSize.width, parentSize.height);
                if (size > 0) {
                    return new Dimension(size, size);
                }
            }
            int size = Math.min(d.width, d.height);
            return new Dimension(size, size);
        }

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(100, 100);
        }
    }
}