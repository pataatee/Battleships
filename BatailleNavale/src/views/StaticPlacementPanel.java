package views;

import controllers.PlacementController;
import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class StaticPlacementPanel extends JPanel {

    private GridPanel _pnlGrid; // grid where placement is gonna be shown
    private PlacementController _pc; // placement controller


    // constructor ofc
    public StaticPlacementPanel(PlacementController pc, Grid grid) {

        // init attributes with parameters
        this._pnlGrid = new GridPanel(grid,true);
        this._pc = pc;

        this.initGrid();

        // init this
        this.init();

    }

    public void initGrid() {
        //this._pc.resetPlacement();
        //this._pc.placeAllObjects();
        this._pnlGrid.repaint();
    }


    // init this
    public void init() {

        this.setLayout(new FlowLayout());
        this.add(this._pnlGrid);

    }

    public void showStaticPlacementPanel(boolean show) {
        this.setVisible(show);
    }

}
