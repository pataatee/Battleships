package views;

import controllers.GameController;
import models.grid.Grid;
import models.grid.GridObserver;
import models.grid.TileState;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel implements GridObserver {

    private Grid _grid;
    private JLayeredPane layeredPane;
    private JPanel gridContainer;
    private JPanel buttonOverlay;
    private boolean isPlayerView;
    private GameController _controller;
    public GridPanel(GameController gc,Grid grid, boolean isPlayerView){
        this._grid = grid;
        this.isPlayerView = isPlayerView;

        int size = grid.getSize();
        int cellSize = 30;
        int totalSize = size * cellSize;

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(totalSize, totalSize));

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);

        initGrid(size, cellSize);

        if (!isPlayerView) {
            initAttackButtons(size, cellSize);
        }
        _controller = gc;
    }

    private void initGrid(int size, int cellSize){
        gridContainer = new JPanel(new GridLayout(size, size, 0, 0));
        gridContainer.setBounds(0, 0, size * cellSize, size * cellSize);

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

        layeredPane.add(gridContainer, 0);
    }

    private void initAttackButtons(int size, int cellSize){
        buttonOverlay = new JPanel(new GridLayout(size, size, 0, 0));
        buttonOverlay.setBounds(0, 0, size * cellSize, size * cellSize);
        buttonOverlay.setOpaque(false);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int x = i;
                final int y = j;

                JButton attackButton = new JButton();
                attackButton.setOpaque(false);
                attackButton.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                attackButton.addActionListener(evt->onCellAttacked(x,y));

                buttonOverlay.add(attackButton);
            }
        }

        layeredPane.add(buttonOverlay, 1);
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
            }
        }
        else {
            switch (state){
                case MISS -> { return Color.WHITE; }
                case BOATHIT -> { return Color.RED; }
                case TRAPHIT -> { return Color.ORANGE; }
                case BOATDEAD -> { return Color.BLACK; }
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
}