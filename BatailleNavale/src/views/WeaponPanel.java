package views;

import controllers.WeaponController;

import javax.swing.*;
import java.awt.*;

public class WeaponPanel extends JPanel {

    private WeaponButton _btnSonar;
    private WeaponButton _btnMissile;
    private WeaponButton _btnBomb;

    private JPanel _panel;

    private WeaponController _controller;

    public WeaponPanel(WeaponController controller) {

        this._controller = controller;
        this.initWeaponPanel();
        this.initWeaponButtons();

    }

    public void initWeaponPanel() {

        this._panel = new JPanel();
        this._panel.setLayout(new FlowLayout());
        this._panel.setPreferredSize(new Dimension(300, 300));
        this.add(_panel);

    }

    public void initWeaponButtons() {

        this._btnBomb = new WeaponButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/bomb.png");
        this._btnSonar = new WeaponButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/sonar.jpg");
        this._btnMissile = new WeaponButton(50, 50, "a31-bataille-navale/BatailleNavale/assets/missile.png");

        this._panel.add(_btnBomb);
        this._panel.add(_btnMissile);
        this._panel.add(_btnSonar);

        this._btnSonar.addActionListener(act -> {
            this._controller.setSonar();
        });

        this._btnBomb.addActionListener(act -> {
            this._controller.setBomb();
        });

        this._btnMissile.addActionListener(act -> {
            this._controller.setMissile();
        });

    }
}
