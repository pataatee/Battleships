package views;

import controllers.GameController;
import models.grid.Grid;
import models.grid.GridObserver;
import models.grid.TileState;

import javax.swing.*;
import java.awt.*;

public class BordelPanel extends JPanel implements GridObserver {

    private JPanel gridContainer;
    private JPanel buttonOverlay;
    private JLayeredPane layeredPane;
    private Grid _grid;
    private GameController _controller;
    private boolean isPlayerView;
    private PlacementView _delegate;
    private JLabel[][] _cells;


    public BordelPanel(Grid grid, boolean isPlayerView) {
        this._grid = grid;
        this.isPlayerView = isPlayerView;

        //debug
        System.out.println("=== BordelPanel créé ===");
        System.out.println("Grid hashCode: " + grid.hashCode());
        System.out.println("isPlayerView: " + isPlayerView);

        this._grid.addObserver(this);

        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initGrid(10);
        initAttackButtons(10);
        add(layeredPane,BorderLayout.CENTER);
    }

    private void initGrid(int size){
        this._cells = new JLabel[size][size];
        gridContainer = new JPanel(new GridLayout(size, size, 0, 0));

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                //JLabel cell = new JLabel();
                //debug
                JLabel cell = new JLabel(y + "," + x);
                cell.setBackground(getTileColor(x, y));
                cell.setOpaque(true);
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                this._cells[y][x] = cell;
                gridContainer.add(cell);
            }
        }
    }

    private void initAttackButtons(int size){
        buttonOverlay = new JPanel(new GridLayout(size, size, 0, 0));
        buttonOverlay.setOpaque(false);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int x = j;
                final int y = i;

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

    @Override
    public void updateTileState(int x , int y ,TileState state) {
        //debug
        System.out.println(">>> BordelPanel.updateTileState appelé ! x=" + x + ", y=" + y + ", state=" + state);
        this._cells[y][x].setBackground(getTileColor(x, y));
        this._cells[y][x].repaint();
    }
}
