package views;

import controllers.WeaponController;
import models.player.WeaponObserver;
import models.weapon.WeaponType;

import javax.swing.*;
import java.awt.*;

public class WeaponPanel extends JPanel implements WeaponObserver {

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

        this._btnBomb.deactivateButton(false);
        this._btnSonar.deactivateButton(false);
        this._btnMissile.setSelectedLayer();

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

    @Override
    public void notifySelected(WeaponType wp) {
        switch (wp) {
            case MISSILE -> {
                this._btnMissile.setSelectedLayer();
                this._btnSonar.setNotSelectedLayer();
                this._btnBomb.setNotSelectedLayer();
            }
            case BOMB -> {
                this._btnMissile.setNotSelectedLayer();
                this._btnSonar.setNotSelectedLayer();
                this._btnBomb.setSelectedLayer();
            }
            case SONAR -> {
                this._btnMissile.setNotSelectedLayer();
                this._btnSonar.setSelectedLayer();
                this._btnBomb.setNotSelectedLayer();
            }
        }

    }

    @Override
    public void notifyUnlocked(WeaponType wp, boolean unlocked) {
        switch (wp) {
            case MISSILE -> {
                _btnMissile.deactivateButton(unlocked);
            }
            case BOMB -> {
                _btnBomb.deactivateButton(unlocked);
            }
            case SONAR -> {
                _btnSonar.deactivateButton(unlocked);
            }
        }

    }
}
