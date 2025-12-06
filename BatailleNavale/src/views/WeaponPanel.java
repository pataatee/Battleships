package views;

import controllers.WeaponController;

import javax.swing.*;
import java.awt.*;

public class WeaponPanel extends JPanel {

    private ButtonBox _btnSonar;
    private ButtonBox _btnMissile;
    private ButtonBox _btnBomb;

    private JLayeredPane _panel;

    private WeaponController _controller;

    public WeaponPanel(WeaponController controller) {

        this._controller = controller;
        this.initWeaponPanel();
        this.initWeaponButtons();

    }

    public void initWeaponPanel() {

        this._panel = new JLayeredPane();
        this._panel.setLayout(new FlowLayout());
        this._panel.setPreferredSize(new Dimension(300, 300));
        this.add(_panel);

    }

    public void initWeaponButtons() {

        this._btnBomb = new ButtonBox(new ImageButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/bomb.png"), "Bomb");
        this._btnSonar = new ButtonBox(new ImageButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/sonar.jpg"), "Sonar");
        this._btnMissile = new ButtonBox(new ImageButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/missile.png"), "Missile");

        this._panel.add(_btnBomb);
        this._panel.add(_btnMissile);
        this._panel.add(_btnSonar);

        this._btnSonar.getBtnImg().addActionListener(act -> {
            this._controller.setSonar();
        });

        this._btnBomb.getBtnImg().addActionListener(act -> {
            this._controller.setBomb();
        });

        this._btnMissile.getBtnImg().addActionListener(act -> {
            this._controller.setMissile();
        });

    }
}
