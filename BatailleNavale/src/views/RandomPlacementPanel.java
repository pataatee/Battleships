package views;

import controllers.PlacementController;
import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class RandomPlacementPanel extends JPanel {

    private PlacementController _pc;
    private GridPanel _pnlGrid;

    public RandomPlacementPanel(PlacementController pc, Grid grid) {

        this._pc = pc;
        this._pnlGrid = new GridPanel(grid, true);
        ;

        this.setLayout(new FlowLayout());
        this.add(_pnlGrid);

    }


}
