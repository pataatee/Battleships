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

        this._btnBomb = new ButtonBox(new ImageButton(50, 50, getClass().getResource("/assets/bomb.png").getPath()), "Bomb");
        this._btnSonar = new ButtonBox(new ImageButton(50, 50, getClass().getResource("/assets/sonar.jpg").getPath()), "Sonar");
        this._btnMissile = new ButtonBox(new ImageButton(50, 50, getClass().getResource("/assets/missile.png").getPath()), "Missile");

        //this._btnMissile.setActivatedLabel(true);


        this._panel.add(_btnBomb);
        this._panel.add(_btnMissile);
        this._panel.add(_btnSonar);

        this._btnSonar.getBtnImg().addActionListener(act -> {
            this._controller.setSonar();
            /*this._btnMissile.setActivatedLabel(false);
            this._btnSonar.setActivatedLabel(true);
            this._btnBomb.setActivatedLabel(false);*/

            //this._btnMissile.setSelectedLayer(false);
            this._btnSonar.setSelectedLayer();
            this._btnMissile.setNotSelectedLayer();
            this._btnBomb.setNotSelectedLayer();
            //this._btnBomb.setSelectedLayer(false);

        });

        this._btnBomb.getBtnImg().addActionListener(act -> {
            this._controller.setBomb();
            /*this._btnMissile.setActivatedLabel(false);
            this._btnSonar.setActivatedLabel(false);
            this._btnBomb.setActivatedLabel(true);*/

            this._btnMissile.setNotSelectedLayer();
            this._btnSonar.setNotSelectedLayer();
            this._btnBomb.setSelectedLayer();
        });

        this._btnMissile.getBtnImg().addActionListener(act -> {
            this._controller.setMissile();
            /*this._btnMissile.setActivatedLabel(true);
            this._btnSonar.setActivatedLabel(false);
            this._btnBomb.setActivatedLabel(false);*/

            this._btnMissile.setSelectedLayer();
            this._btnSonar.setNotSelectedLayer();
            this._btnBomb.setNotSelectedLayer();
        });

    }
}
