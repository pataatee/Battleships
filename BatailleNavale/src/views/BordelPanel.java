package views;

import controllers.GameController;
import models.grid.Grid;
import models.grid.TileState;

import javax.swing.*;
import java.awt.*;

public class BordelPanel extends JPanel {

    private JPanel gridContainer;
    private JPanel buttonOverlay;
    private JLayeredPane layeredPane;
    private Grid _grid;
    private GameController _controller;
    private boolean isPlayerView;
    private PlacementView _delegate;

    public BordelPanel(Grid grid, boolean isPlayerView) {
        this._grid = grid;
        this.isPlayerView = isPlayerView;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initGrid(10);
        initAttackButtons(10);
        add(layeredPane,BorderLayout.CENTER);
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
        _delegate.getPosOfPos(x,y);
    }


    public void addDelegate(PlacementView placementView) {
        _delegate = placementView;
    }
}
