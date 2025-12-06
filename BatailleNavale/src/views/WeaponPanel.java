package views;

import controllers.WeaponController;

import javax.swing.*;
import java.awt.*;

public class WeaponPanel extends JPanel {

    private JButton _rdbSonar;
    private JRadioButton _rdbMissile;
    private JRadioButton _rdbBomb;

    private JPanel _panel;

    private WeaponController _controller;

    public WeaponPanel(WeaponController controller) {
        this._panel = new JPanel();

        this._panel.setLayout(new FlowLayout());
        this.add(_panel);
        this._controller = controller;


        ImageIcon icon = new ImageIcon("a31-bataille-navale/BatailleNavale/assets/sonar.jpg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(newImg);


        _panel.setPreferredSize(new Dimension(300, 300));
        this._rdbBomb = new JRadioButton("Bombe");
        this._rdbMissile = new JRadioButton("Missile");
        this._rdbSonar = new JButton(newIcon);
        ButtonGroup group = new ButtonGroup();
        group.add(_rdbBomb);
        group.add(_rdbMissile);
        group.add(_rdbSonar);
        this._panel.add(_rdbBomb);
        this._panel.add(_rdbMissile);
        this._panel.add(_rdbSonar);

        this._rdbSonar.addActionListener(act -> {
            this._controller.setSonar();
        });

        this._rdbBomb.addActionListener(act -> {
            this._controller.setBomb();
        });

        this._rdbMissile.addActionListener(act -> {
            this._controller.setMissile();
        });
    }
}
