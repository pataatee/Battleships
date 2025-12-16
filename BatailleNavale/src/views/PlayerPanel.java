package views;

import controllers.GameController;
import models.grid.Grid;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private WeaponPanel _weaponPanel;
    private GridPanel _gridPanel;

    public PlayerPanel(GameController gc, Grid grid, boolean isPlayerView, WeaponPanel weaponPanel) {
        this._gridPanel = new GridPanel(gc, grid, isPlayerView);
        this._weaponPanel = weaponPanel;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);
        gbc.gridx = isPlayerView ? 1 : 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.gridheight = 2;
        add(blackPanel, gbc);

        gbc.gridx = isPlayerView ? 0 : 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        gbc.gridheight = 1;
        add(_gridPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        add(_weaponPanel, gbc);
    }
}