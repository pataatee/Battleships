package views;

import javax.swing.*;
import java.awt.*;

public class PlacementInfoPanel extends JPanel {

    private JLabel _lblInfoSelectedPl;
    private JLabel _lblInfoPosition;
    private JLabel _lblInfoOr;
    private JLabel _lblError;

    public PlacementInfoPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(200, 200));

        this._lblInfoSelectedPl = new JLabel("Placeable: ");
        this._lblInfoPosition = new JLabel("Position: ");
        this._lblInfoOr = new JLabel("Orientation: ");
        this._lblError = new JLabel("Erreur: ");

        this.resetInfoLabels();

        this._lblError.setForeground(new Color(0xD50505));
        this._lblError.setVisible(false);

        this.add(this._lblInfoSelectedPl);
        this.add(this._lblInfoPosition);
        this.add(this._lblInfoOr);
        this.add(this._lblError);
    }

    public JLabel getLblError() {
        return this._lblError;
    }

    public void setLblErrorText(String text) {
        this._lblError.setText("<html>Erreur: " + text + "</html>");
    }

    public void setLblSelectedPl(String text) {
        this._lblInfoSelectedPl.setText("Placeable: " + text);
    }

    public void setLblPosition(String text) {
        this._lblInfoPosition.setText("Position: " + text);
    }

    public void setLblOrientation(String text) {
        this._lblInfoOr.setText("Orientation: " + text);
    }

    public void resetInfoLabels() {
        this.setLblSelectedPl("None");
        this.setLblPosition("Null");
        this.setLblOrientation("HORIZONTAL");
        this.getLblError().setVisible(false);
    }

}
