package views;

import controllers.ConfigController;
import controllers.GameController;
import models.game.GameMode;
import models.game.GameState;
import models.placeable.PlaceableFactory;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {

    private final ConfigController controller;
    private final PlaceableFactory factory = new PlaceableFactory();
    private final GameController _gameController;

    private final JLabel lblCasesUsed = new JLabel("Cases utilisées : 0");

    private final JSpinner spinnerCruiser;
    private final JSpinner spinnerDestroyer;
    private final JSpinner spinnerSub;
    private final JSpinner spinnerTorpedo;
    private final JSpinner spinnerAircraftCarrier;

    private final JCheckBox islandBox;

    private final JButton nextBtn;

    private int nbCruiser = 0, nbDestroyer = 0, nbSub = 0, nbTorpedo = 0, nbAircraft = 0;

    public ConfigPanel(ConfigController controller, GameController gameController) {
        this.controller = controller;
        this._gameController = gameController;

        spinnerCruiser = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        spinnerDestroyer = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        spinnerSub = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        spinnerTorpedo = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        spinnerAircraftCarrier = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));

        islandBox = new JCheckBox("Activer le mode île");

        nextBtn = new JButton("Passer au placement");

        this.setSize(600, 600);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Configuration de la partie", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        center.add(islandBox, gbc);
        y++;

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = y;
        center.add(new JLabel("Porte-Avions :"), gbc);
        gbc.gridx = 1;
        center.add(spinnerAircraftCarrier, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        center.add(new JLabel("Croiseurs :"), gbc);
        gbc.gridx = 1;
        center.add(spinnerCruiser, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        center.add(new JLabel("Destroyers :"), gbc);
        gbc.gridx = 1;
        center.add(spinnerDestroyer, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        center.add(new JLabel("Sous-marins :"), gbc);
        gbc.gridx = 1;
        center.add(spinnerSub, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        center.add(new JLabel("Torpilleurs :"), gbc);
        gbc.gridx = 1;
        center.add(spinnerTorpedo, gbc);
        y++;

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        lblCasesUsed.setFont(new Font("Arial", Font.BOLD, 14));
        lblCasesUsed.setForeground(Color.BLUE);
        center.add(lblCasesUsed, gbc);

        this.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        nextBtn.setFont(new Font("Arial", Font.BOLD, 16));
        nextBtn.setBackground(new Color(70, 130, 180));
        nextBtn.setForeground(Color.WHITE);
        bottom.add(nextBtn);
        this.add(bottom, BorderLayout.SOUTH);

        spinnerCruiser.addChangeListener(e -> updateBoatCount(BoatType.CRUISER, (int) spinnerCruiser.getValue(), factory.createCruiser()));
        spinnerDestroyer.addChangeListener(e -> updateBoatCount(BoatType.DESTROYER, (int) spinnerDestroyer.getValue(), factory.createDestroyer()));
        spinnerSub.addChangeListener(e -> updateBoatCount(BoatType.SUBMARINE, (int) spinnerSub.getValue(), factory.createSubmarine()));
        spinnerTorpedo.addChangeListener(e -> updateBoatCount(BoatType.TORPEDOBOAT, (int) spinnerTorpedo.getValue(), factory.createTorpedoBoat()));
        spinnerAircraftCarrier.addChangeListener(e -> updateBoatCount(BoatType.AIRCRAFTCARRIER, (int) spinnerAircraftCarrier.getValue(), factory.createAircraftCarrier()));

        islandBox.addActionListener(e -> updateGameMode());

        nextBtn.addActionListener(e -> validateAndProceed());

        updateGameMode();
        updateAllBoatCounts();
    }

    private void updateAllBoatCounts() {
        updateBoatCount(BoatType.CRUISER, (int) spinnerCruiser.getValue(), factory.createCruiser());
        updateBoatCount(BoatType.DESTROYER, (int) spinnerDestroyer.getValue(), factory.createDestroyer());
        updateBoatCount(BoatType.SUBMARINE, (int) spinnerSub.getValue(), factory.createSubmarine());
        updateBoatCount(BoatType.TORPEDOBOAT, (int) spinnerTorpedo.getValue(), factory.createTorpedoBoat());
        updateBoatCount(BoatType.AIRCRAFTCARRIER, (int) spinnerAircraftCarrier.getValue(), factory.createAircraftCarrier());
    }

    private void updateGameMode() {
        if (islandBox.isSelected()) {
            controller.SelectGameMode(GameMode.ISLAND);
        } else {
            controller.SelectGameMode(GameMode.NORMAL);
        }
        updateCasesUsedDisplay();
    }

    private void updateBoatCount(BoatType type, int newValue, Boat boatTemplate) {
        int currentCount = getCurrentCount(type);
        int diff = newValue - currentCount;

        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                boolean ok = controller.selectBoat(boatTemplate);
                if (!ok) {
                    JOptionPane.showMessageDialog(this,
                            "Impossible d'ajouter ce bateau : limite de cases dépassée !",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    setSpinnerValue(type, currentCount);
                    return;
                }
                incrementCount(type);
            }
        } else if (diff < 0) {
            for (int i = 0; i < -diff; i++) {
                boolean ok = controller.deselectBoat(type);
                if (ok) {
                    decrementCount(type);
                }
            }
        }

        updateCasesUsedDisplay();
    }

    private void updateCasesUsedDisplay() {
        int totalCases = controller.getTotalCases();
        int maxCells = 35;

        String text = String.format("Cases utilisées : %d / %d", totalCases, maxCells);
        lblCasesUsed.setText(text);

        if (totalCases > maxCells * 0.8) {
            lblCasesUsed.setForeground(Color.RED);
        } else if (totalCases > maxCells * 0.6) {
            lblCasesUsed.setForeground(Color.ORANGE);
        } else {
            lblCasesUsed.setForeground(Color.BLUE);
        }
    }

    private void validateAndProceed() {
        updateGameMode();

        if (controller.getTotalCases() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Vous devez sélectionner au moins un bateau !",
                    "Configuration incomplète",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nbAircraft > 3 || nbCruiser > 3 || nbDestroyer > 3 || nbSub > 3 || nbTorpedo > 3) {
            JOptionPane.showMessageDialog(this,
                    "Vous ne pouvez pas sélectionner plus de 3 bateaux de chaque type !",
                    "Limite dépassée",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        _gameController.setState(GameState.PLACEMENT);
        _gameController.confirmConfig();
    }

    private int getCurrentCount(BoatType type) {
        switch (type) {
            case CRUISER:
                return nbCruiser;
            case DESTROYER:
                return nbDestroyer;
            case SUBMARINE:
                return nbSub;
            case TORPEDOBOAT:
                return nbTorpedo;
            case AIRCRAFTCARRIER:
                return nbAircraft;
            default:
                return 0;
        }
    }

    private void incrementCount(BoatType type) {
        switch (type) {
            case CRUISER:
                nbCruiser++;
                break;
            case DESTROYER:
                nbDestroyer++;
                break;
            case SUBMARINE:
                nbSub++;
                break;
            case TORPEDOBOAT:
                nbTorpedo++;
                break;
            case AIRCRAFTCARRIER:
                nbAircraft++;
                break;
        }
    }

    private void decrementCount(BoatType type) {
        switch (type) {
            case CRUISER:
                if (nbCruiser > 0) nbCruiser--;
                break;
            case DESTROYER:
                if (nbDestroyer > 0) nbDestroyer--;
                break;
            case SUBMARINE:
                if (nbSub > 0) nbSub--;
                break;
            case TORPEDOBOAT:
                if (nbTorpedo > 0) nbTorpedo--;
                break;
            case AIRCRAFTCARRIER:
                if (nbAircraft > 0) nbAircraft--;
                break;
        }
    }

    private void setSpinnerValue(BoatType type, int value) {
        switch (type) {
            case CRUISER:
                spinnerCruiser.setValue(value);
                break;
            case DESTROYER:
                spinnerDestroyer.setValue(value);
                break;
            case SUBMARINE:
                spinnerSub.setValue(value);
                break;
            case TORPEDOBOAT:
                spinnerTorpedo.setValue(value);
                break;
            case AIRCRAFTCARRIER:
                spinnerAircraftCarrier.setValue(value);
                break;
        }
    }
}