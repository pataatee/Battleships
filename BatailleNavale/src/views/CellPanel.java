package views;

import models.grid.Grid;
import models.grid.GridObserver;
import models.grid.TileState;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel implements GridObserver {

    private Grid _grid;
    private JPanel gridContainer;
    private boolean isPlayerView;

    public CellPanel(Grid grid, boolean isPlayerView) {
        this._grid = grid;
        this.isPlayerView = isPlayerView;

        int size = grid.getSize();
        setLayout(new BorderLayout());

        initGrid(size);
    }

    private void initGrid(int size) {
        gridContainer = new JPanel(new GridLayout(size, size, 0, 0)) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                int squareSize = Math.min(d.width, d.height);
                return new Dimension(squareSize, squareSize);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel cell = new JLabel() {
                    @Override
                    public Dimension getPreferredSize() {
                        Dimension d = super.getPreferredSize();
                        int size = Math.max(d.width, d.height);
                        return new Dimension(size, size);
                    }
                };
                cell.setBackground(getTileColor(TileState.EMPTY));
                cell.setOpaque(true);
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                gridContainer.add(cell);
            }
        }

        add(gridContainer, BorderLayout.CENTER);
    }

    private Color getTileColor(TileState tileState) {

        if (isPlayerView) {
            switch (tileState) {
                case EMPTY -> {
                    return Color.CYAN;
                }
                case MISS -> {
                    return Color.WHITE;
                }
                case BOAT -> {
                    return Color.DARK_GRAY;
                }
                case BOATHIT -> {
                    return Color.RED;
                }
                case BOATDEAD -> {
                    return Color.BLACK;
                }
                case TRAP -> {
                    return Color.ORANGE;
                }
                case TRAPHIT -> {
                    return Color.YELLOW;
                }
                case SEARCHED -> {
                    return Color.DARK_GRAY;
                }
                case NOTSEARCHED -> {
                    return Color.MAGENTA;
                }
                case ISLAND -> {
                    return Color.yellow;
                }
                case ISLANDHIT -> {
                    return Color.BLUE;
                }
            }
        } else {
            switch (tileState) {
                case MISS -> {
                    return Color.WHITE;
                }
                case BOATHIT -> {
                    return Color.RED;
                }
                case TRAPHIT -> {
                    return Color.ORANGE;
                }
                case BOATDEAD -> {
                    return Color.BLACK;
                }
                case ISLAND -> {
                    return Color.yellow;
                }
                case ISLANDHIT -> {
                    return Color.BLUE;
                }
                default -> {
                    return Color.CYAN;
                }
            }
        }

        return Color.PINK;
    }

    @Override
    public void updateTileState(int x, int y, TileState state) {
        int size = _grid.getSize();
        int index = x * size + y;
        JLabel cell = (JLabel) gridContainer.getComponent(index);
        cell.setBackground(getTileColor(state));
        cell.repaint();
        this.updateUI();
    }

    @Override
    public Dimension getPreferredSize() {
        int cellSize = 30;
        int size = _grid.getSize() * cellSize;
        return new Dimension(size, size);
    }

}