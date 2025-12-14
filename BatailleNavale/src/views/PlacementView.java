package views;

import controllers.PlacementController;

import javax.swing.*;
import java.awt.*;

public class PlacementView extends JPanel {

    private JPanel _placeableButtons; // le truc dans lequel il y aura tous les boutons
    private JScrollPane _scrPlaceableButtons;
    private GridPanel _gridPan; // la grille sur laquelle on place les objets
    private JButton _next; // pour aller à l'écran suivant (jeu)
    private JButton _previous; // pour aller à l'écran précédent (config)
    private PlacementController _pc;
    private JPanel _panButtons;

    public PlacementView(PlacementController pc, GridPanel gp) {

        this._pc = pc;
        this._gridPan = gp;
        this.initPanelPlButtons();
        this.initPlaceableButtons();
        this.initPlacementView();

    }

    public void initPlaceableButtons() {
        for (int i = 0; i < this._pc.getNbPlaceables(); i++) {
            JButton btn = new JButton(this._pc.getPlName(i));
            this._placeableButtons.add(btn);
        }
    }

    private void initPlacementView() {

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 800));
        //this.add(this._scrPlaceableButtons, BorderLayout.WEST);

        this.add(this._panButtons, BorderLayout.WEST);

        JPanel panNextPrev = new JPanel();
        panNextPrev.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panNextPrev.setPreferredSize(new Dimension(300, 50));
        panNextPrev.setLayout(new BorderLayout());
        this._next = new JButton("Next");
        this._next.setPreferredSize(new Dimension(90, 70));
        panNextPrev.add(this._next, BorderLayout.EAST);
        this._previous = new JButton("Previous");
        this._previous.setPreferredSize(new Dimension(90, 70));
        panNextPrev.add(this._previous, BorderLayout.WEST);
        this.add(panNextPrev, BorderLayout.SOUTH);

        //this._gridPan.setPreferredSize(new Dimension(300, 300));
        this.add(this._gridPan, BorderLayout.EAST);

    }

    private void initPanelPlButtons() {

        this._panButtons = new JPanel();
        this._panButtons.setLayout(new BorderLayout());
        this._panButtons.setPreferredSize(new Dimension(250, 370));

        this._placeableButtons = new JPanel();
        this._placeableButtons.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
        this._placeableButtons.setLayout(new BoxLayout(this._placeableButtons, BoxLayout.Y_AXIS));

        this._scrPlaceableButtons = new JScrollPane(this._placeableButtons);
        this._scrPlaceableButtons.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 5));
        this._scrPlaceableButtons.setPreferredSize(new Dimension(180,350));

//        JButton btnOr = new JButton("Touurne");
//        btnOr.setPreferredSize(new Dimension(60, 30));
        JPanel rightPanel = new JPanel(new BorderLayout());
        ImageButton btnOr = new ImageButton(40, 40, getClass().getResource("/assets/rotate.png").getPath());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        rightPanel.add(btnOr, BorderLayout.NORTH);

        this._panButtons.add(this._scrPlaceableButtons, BorderLayout.CENTER);
        this._panButtons.add(rightPanel, BorderLayout.EAST);
    }

    public void showPlacementView(Boolean show) {
        this.setVisible(show);
    }

}
