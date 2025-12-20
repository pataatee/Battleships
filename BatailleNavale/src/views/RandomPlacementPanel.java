package views;

import controllers.PlacementController;

import javax.swing.*;
import java.awt.*;

public class RandomPlacementPanel extends JPanel {

    private PlacementController _pc;
    private BordelPanel _pnlGrid;

    public RandomPlacementPanel(PlacementController pc, BordelPanel bp) {

        this._pc = pc;
        this._pnlGrid = bp;

        this.setLayout(new FlowLayout());
        this.add(_pnlGrid);

    }


}
