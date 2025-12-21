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
        initPlayerPanel();
    }

    public PlayerPanel(Grid grid, boolean isPlayerView, WeaponPanel weaponPanel) {
        this._gridPanel = new GridPanel(grid, isPlayerView);
        this._weaponPanel = weaponPanel;
        initPlayerPanel();
    }

    private void initPlayerPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;

        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        add(_gridPanel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        add(_weaponPanel, gbc);
    }
}