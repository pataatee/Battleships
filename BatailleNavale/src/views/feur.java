package views;

import controllers.PlacementController;
import models.game.placement.Orientation;
import models.placeable.Placeable;

import javax.swing.*;
import java.awt.*;

public class feur extends JPanel {

    private JPanel _placeableButtons; // le truc dans lequel il y aura tous les boutons
    private JScrollPane _scrPlaceableButtons;
    private BordelPanel _gridPan; // la grille sur laquelle on place les objets
    private JButton _next; // pour aller à l'écran suivant (jeu)
    private JButton _previous; // pour aller à l'écran précédent (config)
    private PlacementController _pc;
    private JPanel _panButtons;
    private Placeable _currentPlToPlace;
    private int _currentIndexPlToPlace;
    private ImageButton _btnOr;
    private JButton _btnValidate;
    private JButton[] _lstPlaceableButtons;
    private PlacementInfoPanel _pnlInfos;
    private Boolean _coAreChosenYippie;
    private JButton _btnClear;


/*    public PlacementView(PlacementController pc, BordelPanel gp) {

        this._pc = pc;
        this._gridPan = gp;
        this._gridPan.setVisible(true);
        this._coAreChosenYippie = false;
        this.idkfHowToCallThatButItsGunnaBeCoolISwear();
        this.initPanelPlButtons();
        this.initPlaceableButtons();
        this.initPlacementView();
        gp.addDelegate(this);
//
//        JButton btn = new JButton("test");
//        btn.addActionListener(act -> {
//            System.out.println(this._pc.getCoord());
//        });

    }

    public void initPlaceableButtons() { // done

        this._lstPlaceableButtons = new JButton[this._pc.getNbPlaceables()];

        for (int i = 0; i < this._pc.getNbPlaceables(); i++) {
            JButton btn = new JButton(this._pc.getPlName(i));
            this._placeableButtons.add(btn);
            this._lstPlaceableButtons[i] = btn;
            final String k = this._pc.getPlName(i);
            Placeable pl = this._pc.getPl(i);
            int finalI = i;
            btn.addActionListener(act -> {
                this._pnlInfos.getLblError().setVisible(false);
                this._currentPlToPlace = pl;
                this._currentIndexPlToPlace = finalI;
                this._pnlInfos.setLblSelectedPl(this._currentPlToPlace.getName());
                //System.out.println(k);
            });
        }
    }

    private void initPlacementView() {

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 800));
        //this.add(this._scrPlaceableButtons, BorderLayout.WEST);

        this.add(this._panButtons, BorderLayout.WEST);

        // Panel central qui contient les infos + la grille // done
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(this._pnlInfos, BorderLayout.WEST);
        centerPanel.add(this._gridPan, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.EAST);

        // pa, buttons next prev
        JPanel panNextPrev = new JPanel();
        panNextPrev.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNextPrev.setPreferredSize(new Dimension(300, 50));
        panNextPrev.setLayout(new BorderLayout());
        this._next = new JButton("Next");
        this._next.setPreferredSize(new Dimension(90, 70));
        panNextPrev.add(this._next, BorderLayout.EAST);
        this._next.addActionListener(act -> {
            this.setVisible(false);
            // TODO: implémenter dans GameController une maniere de changer d'écran (ou autre controller, idk)
        });
        this._previous = new JButton("Previous");
        this._previous.setPreferredSize(new Dimension(90, 70));
        panNextPrev.add(this._previous, BorderLayout.WEST);
        this._previous.addActionListener(act -> {
            this.setVisible(false);
        });
        this.add(panNextPrev, BorderLayout.SOUTH);

        //this._gridPan.setPreferredSize(new Dimension(300, 300));
        //this.add(this._gridPan, BorderLayout.EAST);

    }

    private void initPanelPlButtons() {

        // done
        this._panButtons = new JPanel();
        this._panButtons.setLayout(new BorderLayout());
        this._panButtons.setPreferredSize(new Dimension(250, 370));

        // done
        this._placeableButtons = new JPanel();
        this._placeableButtons.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        this._placeableButtons.setLayout(new BoxLayout(this._placeableButtons, BoxLayout.Y_AXIS));

        this._scrPlaceableButtons = new JScrollPane(this._placeableButtons);
        this._scrPlaceableButtons.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 5));
        this._scrPlaceableButtons.setPreferredSize(new Dimension(180,350));

//        JButton btnOr = new JButton("Touurne");
//        btnOr.setPreferredSize(new Dimension(60, 30));
        JPanel rightPanel = new JPanel(new BorderLayout());
        this._btnOr = new ImageButton(40, 40, getClass().getResource("/assets/rotate.png").getPath());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        rightPanel.add(this._btnOr, BorderLayout.NORTH);

        this._btnOr.addActionListener(act -> {

            this._pnlInfos.getLblError().setVisible(false);

            switch (this._pc.getCoordOr()) {
                case HORIZONTAL -> {
                    this._pc.setCoordOr(Orientation.VERTICAL);
                    this._pnlInfos.setLblOrientation(this._pc.getCoordOr().toString());
                }
                case VERTICAL -> {
                    this._pc.setCoordOr(Orientation.HORIZONTAL);
                    this._pnlInfos.setLblOrientation(this._pc.getCoordOr().toString());
                }
                default -> {
                    this._pc.setCoordOr(Orientation.HORIZONTAL);
                    this._pnlInfos.setLblOrientation(this._pc.getCoordOr().toString());
                }
            }

            System.out.println("Orientation: " + this._pc.getCoordOr());
        });


        JButton btn = new JButton("test");
        rightPanel.add(btn);
        btn.addActionListener(act -> {
            System.out.println(this._pc.showCoord());
        });
        btn.setVisible(false);

        this._pnlInfos.add(Box.createVerticalStrut(20));

        this._btnValidate = new JButton("Place"); // OK // oui c juste pour trouver plus vite le btn et alors
        this._btnValidate.setMaximumSize(new Dimension(152, 50));
        this._pnlInfos.add(this._btnValidate);
        this._btnValidate.addActionListener(act -> {

            if (this._currentPlToPlace == null) {
                this._pnlInfos.setLblErrorText("Please select a boat <br>or trap.");
                this._pnlInfos.getLblError().setVisible(true);
                return;
            }

            if (!this._coAreChosenYippie) {
                this._pnlInfos.setLblErrorText("Please choose where<br>you're gonna place the<br>placeable.");
                this._pnlInfos.getLblError().setVisible(true);
                return;
            }

            Boolean placed = this._pc.placeObject(this._currentPlToPlace);

            if (placed) {
                System.out.println("Boat/Trap " + this._currentPlToPlace.getName() + " successfully placed !");
                JButton btnToDisable = this._lstPlaceableButtons[this._currentIndexPlToPlace];
                btnToDisable.setEnabled(false);


                // gneh pr eviter les conneries quand on reclique pas sur un nv bouton bato apres avoir placé qqch mdr
                // reset placeable
                this._currentPlToPlace = null;
                this._currentIndexPlToPlace = -1;

                // reset orientation
                this._pc.setCoordOr(Orientation.HORIZONTAL);

                // reset info labels
                this.resetInfoLabels();
            }
            else {
                System.out.println("Error: couldn't place Boat/Trap.");
                this._pnlInfos.setLblErrorText("Couldn't place Boat/Trap. Please try again.");
                this._pnlInfos.getLblError().setVisible(true);
            }
        });

        this._pnlInfos.add(Box.createVerticalStrut(20));

        this._btnClear = new JButton("Reset");
        this._btnClear.setMaximumSize(new Dimension(152, 50));
        this._pnlInfos.add(this._btnClear);
        this._btnClear.addActionListener(act -> {
            this._currentPlToPlace = null;
            this._currentIndexPlToPlace = -1;
            this.resetInfoLabels();
            this._pc.resetPlacement();
            this.resetPlaceableButtons();
            this._coAreChosenYippie = false;
        });

        this._panButtons.add(this._scrPlaceableButtons, BorderLayout.CENTER);
        this._panButtons.add(rightPanel, BorderLayout.EAST);
    }

    public void showPlacementView(Boolean show) {
        this.setVisible(show);
    }


    public void getPosOfPos(int x, int y) {
        this._pnlInfos.getLblError().setVisible(false);
        System.out.println("Veut poser en "+x +" "+y+" ");
        this._pnlInfos.setLblPosition("x:" + x + " y:" + y);
        this._pc.setCoordXY(x, y);
        this._coAreChosenYippie = true;
    }

    public Placeable getCurrentToPlace() {
        return this._currentPlToPlace;
    }

    private void idkfHowToCallThatButItsGunnaBeCoolISwear() {
        this._pnlInfos = new PlacementInfoPanel();
        this._pnlInfos.setLblSelectedPl("None");
        this.resetInfoLabels();
        this.add(this._pnlInfos);

    }

    public void resetPlaceableButtons() {
        for (JButton btn : this._lstPlaceableButtons) {
            btn.setEnabled(true);
        }
    }

    public void resetInfoLabels() {
        this._pnlInfos.setLblSelectedPl("None");
        this._pnlInfos.setLblPosition("Null");
        this._pnlInfos.setLblOrientation("HORIZONTAL");
        this._pnlInfos.getLblError().setVisible(false);
    }

*/
}
