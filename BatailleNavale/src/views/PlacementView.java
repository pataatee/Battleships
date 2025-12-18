package views;

import controllers.PlacementController;
import models.game.placement.Coord;
import models.game.placement.Orientation;
import models.grid.TileState;
import models.placeable.Placeable;

import javax.swing.*;
import java.awt.*;

public class PlacementView extends JPanel {

    private JPanel _pnlNavigate; // contains next/prev
    private JButton _btnNext; // btn next screen
    private JButton _btnPrev; // btn prev screen

    private JPanel _pnlPlacementStrategySelection; // pretty transparent name
    private JLabel _lblPlacementStrategy; // basically just text
    private JComboBox<String> _cboSelectPlacementStrategy; // gonna be stuff to select in there

    private PlacementController _pc; // controller ofc

    private ManualPlacementPanel _pnlManualPlacement; // manual placement panel
    private StaticPlacementPanel _pnlStaticPlacement; // static placement panel
    private JPanel _pnlNoneSelected; // no placement strategy selected

    private JPanel _pnlPlacement;
    private CardLayout _lytPnlPlacement;



    // constructor ofc
    public PlacementView(PlacementController pc, ManualPlacementPanel mpp, StaticPlacementPanel spp) {

        this._pc = pc;
        this._pnlManualPlacement = mpp;
        this._pnlStaticPlacement = spp;

        this.initAttributes();
        this.initThis();

        actionsChangeCbo();

    }


    public void initAttributes() {

        // init buttons navigation
        this._btnNext = new JButton("Next");
        this._btnNext.setPreferredSize(new Dimension(90, 70));

        this._btnPrev = new JButton("Previous");
        this._btnPrev.setPreferredSize(new Dimension(90, 70));


        // init navigation
        this._pnlNavigate = new JPanel();
        this._pnlNavigate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        this._pnlNavigate.setPreferredSize(new Dimension(300, 50));
        this._pnlNavigate.setLayout(new BorderLayout());

        this._pnlNavigate.add(this._btnNext, BorderLayout.EAST);
        this._pnlNavigate.add(this._btnPrev, BorderLayout.WEST);


        // init placement strategy combo box
        this._cboSelectPlacementStrategy = new JComboBox<String>();
        this._cboSelectPlacementStrategy.addItem("Please select a placement method...");
        this._cboSelectPlacementStrategy.addItem("Manual placement");
        this._cboSelectPlacementStrategy.addItem("Random placement");
        this._cboSelectPlacementStrategy.addItem("Static placement");


        // init label placement strategy
        this._lblPlacementStrategy = new JLabel("Placement strategy : ");


        // init panel placement strategy
        this._pnlPlacementStrategySelection = new JPanel(new BorderLayout());

        this._pnlPlacementStrategySelection.add(this._lblPlacementStrategy, BorderLayout.WEST);
        this._pnlPlacementStrategySelection.add(this._cboSelectPlacementStrategy, BorderLayout.CENTER);

        // init none selected panel
        this._pnlNoneSelected = new JPanel();

    }

    public void initThis() {

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 800));

        this.add(this._pnlNavigate, BorderLayout.SOUTH);
        this.add(this._pnlPlacementStrategySelection, BorderLayout.NORTH);

        this._lytPnlPlacement = new CardLayout();
        this._pnlPlacement = new JPanel(this._lytPnlPlacement);

        this._pnlPlacement.add(this._pnlNoneSelected, "NONE");
        this._pnlPlacement.add(this._pnlManualPlacement, "MANUAL");
        this._pnlPlacement.add(this._pnlStaticPlacement, "STATIC");

        this.add(this._pnlPlacement, BorderLayout.CENTER);


    }

    public void actionsChangeCbo() {

        this._cboSelectPlacementStrategy.addActionListener(act -> {

            int index = this._cboSelectPlacementStrategy.getSelectedIndex();

            switch (index) {
                case 0:
                    this._lytPnlPlacement.show(this._pnlPlacement, "NONE");
                    break;
                case 1:
                    this._lytPnlPlacement.show(this._pnlPlacement, "MANUAL");
                    break;
                case 2:
                    this._lytPnlPlacement.show(this._pnlPlacement, "STATIC");
                    break;
                case 3:
                    this._pc.changeStrat("Static");
                    this._pc.placeAllObjects();
                    this._pnlStaticPlacement.repaint();
                    this._lytPnlPlacement.show(this._pnlPlacement, "STATIC");
                    break;
                default:
                    this._pnlManualPlacement.showManualPlacementPanel(false);
                    break;
            }

        });

    }

}
