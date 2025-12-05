package views;

import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private Grid _grid;
    private JLayeredPane pan;  // Le JLayeredPane

    public GridPanel(Grid grid){
        _grid = grid;
        pan = new JLayeredPane();
        pan.setPreferredSize(new Dimension(300, 300));
        setLayout(new BorderLayout());
        add(pan, BorderLayout.CENTER);

        initGrid();
    }

    private void initGrid(){
        int size = _grid.getSize();
        int cellSize =20;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel("Cell");
                label.setBounds(i * cellSize, j * cellSize, cellSize, cellSize);
                label.setBackground(getTileColor(i,j));
                label.setOpaque(true);
                System.out.println(_grid.getTileTileState(i,j));
                pan.add(label, Integer.valueOf(i * size + j));
            }
        }
    }

    private Color getTileColor(int x,int y){
        switch (_grid.getTileTileState(x,y)){
            case EMPTY -> {
                return Color.blue;
            }
            case MISS -> {
                return Color.red;
            }
            case BOAT -> {
                return Color.black;
            }
            case BOATHIT -> {
                return Color.gray;
            }
            case TRAP -> {
                return Color.cyan;
            }
            case TRAPHIT -> {
                return Color.blue;
            }
            case SEARCHED -> {
                return Color.blue;
            }
            case NOTSEARCHED -> {
                return Color.blue;
            }
        }
        return Color.pink;
    }



}
