package views;

import controllers.PlacementController;

import javax.swing.*;

public class PlacementView extends JPanel {

    // le truc ou y'aura tous les boutons dedans
    private JPanel _placeableButtons;
    private GridPanel _gridPan;
    private JButton _next; // pour aller à l'écran suivant (jeu)
    private JButton _previous; // pour aller à l'écran précédent (config)
    private PlacementController _pc;

    public PlacementView() {

    }

    public void initPlaceableButtons() {
        for (int i = 0; i < this._pc.getNbPlaceables(); i++) {
            JButton btn = new JButton(this._pc.getPlName(i));
        }
    }

}
