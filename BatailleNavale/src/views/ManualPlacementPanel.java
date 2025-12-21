package views;

import controllers.PlacementController;
import models.game.placement.Orientation;
import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class ManualPlacementPanel extends JPanel {

    private PlacementController _pc; // le controller
    private GridPanel _pnlGrid; // grid ou sont placés les objets

    private JPanel _pnlForScrPan; // panel a ajouter au scrollPanel
    private JScrollPane _scrPanLstPlButtons; // le scroll pane ds lequel il y a tous les pl buttons
    private JButton[] _lstBtnPl; // liste des buttons

    private JPanel _pnlOrientation; // panel contenant le btn pour l'orientation/...
    private ImageButton _btnOrientation; // btn switch rotation

    private JPanel _pan; // panel contenant PlacementInfo, btn valider, btn erase
    private PlacementInfoPanel _pnlInfos; // les infos sur l'endroit du placement des pl
    private JButton _btnPlace; // place le placeable
    private JButton _btnEraseAll; // reset le placement

    private JPanel _pnlLeft; // uhh to organize stuff (scr panel, rota button)
    private JPanel _pnlRight; // to organize stuff again lol (pnl infos, grid)

    private int _currentIndexPlToPlace; // current index of the boat to place
    private boolean _coSelected; // true if co are selected


    public ManualPlacementPanel(PlacementController pc, Grid grid) {
        // init with parameters
        this._pc = pc;
        this._pnlGrid = new GridPanel(grid,false);

        // init all
        this.initAttributes();

        // init with methods
        this.initLstButtonsPl();
        this.actionsOnBtnClic();

        // init this
        this.initThis();
        _pnlGrid.createGridCenter(10,(x,y)->getPosOfPos(x,y));

    }

    // init all attributes, place them nicely in different panels
    private void initAttributes() {

        // init pl button list
        this._lstBtnPl = new JButton[this._pc.getNbPlaceables()];

        // init pnl for the scrollable one
        this._pnlForScrPan = new JPanel();
        this._pnlForScrPan.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        this._pnlForScrPan.setLayout(new BoxLayout(this._pnlForScrPan, BoxLayout.Y_AXIS));

        // init scrollable panel
        this._scrPanLstPlButtons = new JScrollPane(this._pnlForScrPan);
        this._scrPanLstPlButtons.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 5));
        this._scrPanLstPlButtons.setPreferredSize(new Dimension(180, 350));

        // init orientation
        this._pnlOrientation = new JPanel(new BorderLayout());
        this._pnlOrientation.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        this._btnOrientation = new ImageButton(40, 40, getClass().getResource("/assets/rotate.png").getPath());
        this._pnlOrientation.add(this._btnOrientation, BorderLayout.NORTH);

        // init info
        this._pnlInfos = new PlacementInfoPanel();

        this._pan = new JPanel(new BorderLayout());
        this._pan.add(this._pnlInfos, BorderLayout.WEST);


        // init place/erase all buttons
        this._btnPlace = new JButton("Place"); // "OK"
        this._btnPlace.setMaximumSize(new Dimension(152, 50));
        this._pnlInfos.add(this._btnPlace);
        this._btnEraseAll = new JButton("Reset");
        this._btnEraseAll.setMaximumSize(new Dimension(152, 50));
        this._pnlInfos.add(this._btnEraseAll);

        // init ints/booleans
        this._coSelected = false;
        this._currentIndexPlToPlace = -1;

        // init left/right panels
        this._pnlLeft = new JPanel(new BorderLayout());
        this._pnlLeft.add(this._scrPanLstPlButtons, BorderLayout.WEST);
        this._pnlLeft.add(this._pnlOrientation, BorderLayout.CENTER);

        this._pnlRight = new JPanel(new BorderLayout());
        this._pnlRight.add(this._pan, BorderLayout.WEST);
        this._pnlRight.add(this._pnlGrid, BorderLayout.EAST);


    }


    public void initThis() {

        this.setLayout(new BorderLayout());

        this.add(this._pnlLeft, BorderLayout.WEST);
        this.add(this._pnlRight, BorderLayout.CENTER);

    }


    // creates action listeners for place/reset buttons and rotation
    public void actionsOnBtnClic() {

        // btn place action listener
        this._btnPlace.addActionListener(act -> {

            if (this._pc.getPlFromIndex(this._currentIndexPlToPlace) == null) {
                this._pnlInfos.setLblErrorText("Please select a boat <br>or trap.");
                this._pnlInfos.getLblError().setVisible(true);
                return;
            }

            if (!this._coSelected) {
                this._pnlInfos.setLblErrorText("Please choose where<br>you're gonna place the<br>placeable.");
                this._pnlInfos.getLblError().setVisible(true);
                return;
            }

            Boolean placed = this._pc.placeObject(this._pc.getPlFromIndex(this._currentIndexPlToPlace));

            if (placed) {

                System.out.println("Boat/Trap " + this._pc.getPlNameFromIndex(this._currentIndexPlToPlace) + " successfully placed !");
                JButton btnToDisable = this._lstBtnPl[this._currentIndexPlToPlace];
                btnToDisable.setEnabled(false);

                // reset labels, coordinates, placeable selected after placing a pl
                // reset placeable
                this._currentIndexPlToPlace = -1;

                // reset orientation
                this._pc.setCoordOr(Orientation.HORIZONTAL);

                // reset info labels
                this._pnlInfos.resetInfoLabels();

            } else {

                System.out.println("Error: couldn't place Boat/Trap.");
                this._pnlInfos.setLblErrorText("Couldn't place Boat/Trap. Please try again.");
                this._pnlInfos.getLblError().setVisible(true);

            }

            this._coSelected = false;
        });

        // btn erase all action listener
        this._btnEraseAll.addActionListener(act -> {

            this._pc.setPlaceableAtIndex(null, this._currentIndexPlToPlace);
            this._currentIndexPlToPlace = -1;
            this._pnlInfos.resetInfoLabels();
            this._pc.resetPlacement();
            this.resetPlaceableButtons();
            this._coSelected = false;

        });

        // btn orientation action listener
        this._btnOrientation.addActionListener(act -> {

            this._pnlInfos.getLblError().setVisible(false);

            switch (this._pc.getCoordOr()) {
                case HORIZONTAL -> {
                    this._pc.setCoordOr(Orientation.VERTICAL);
                    this._pnlInfos.setLblOrientation(this._pc.getCoordOr().toString());
                }
                default -> {
                    this._pc.setCoordOr(Orientation.HORIZONTAL);
                    this._pnlInfos.setLblOrientation(this._pc.getCoordOr().toString());
                }
            }

            System.out.println("Orientation: " + this._pc.getCoordOr());
        });

    }


    // init le ScrollPane avec les buttons de chaque pl à placer
    public void initLstButtonsPl() {

        for (int i = 0; i < this._pc.getNbPlaceables(); i++) {

            JButton btn = new JButton(this._pc.getPlName(i));
            this._pnlForScrPan.add(btn);
            this._lstBtnPl[i] = btn;
            final String k = this._pc.getPlName(i);
            int finalI = i;
            btn.addActionListener(act -> {

                this._pnlInfos.getLblError().setVisible(false);
                this._currentIndexPlToPlace = finalI;
                this._pnlInfos.setLblSelectedPl(this._pc.getPlNameFromIndex(finalI));

            });
        }

    }


    // useful stuff
    // resets the placeable buttons
    public void resetPlaceableButtons() {
        for (JButton btn : this._lstBtnPl) {
            btn.setEnabled(true);
        }
    }

    // positions magie noire
    public void getPosOfPos(int x, int y) {
        this._pnlInfos.getLblError().setVisible(false);
        System.out.println("Veut poser en " + x + " " + y + " ");
        this._pnlInfos.setLblPosition("x:" + x + " y:" + y);
        this._pc.setCoordXY(x, y);
        this._coSelected = true;
    }

    public void showManualPlacementPanel(Boolean show) {
        this.setVisible(show);
    }


}
