package views;

import controllers.GameController;

import javax.swing.*;
import java.awt.*;

public class ButtonGridPanel extends JPanel {

    private JPanel buttonOverlay;
    private GameController _controller;
    private int gridSize;

    public ButtonGridPanel(GameController controller, int gridSize) {
        this._controller = controller;
        this.gridSize = gridSize;

        setOpaque(false);
        setLayout(new GridLayout(gridSize, gridSize, 0, 0));

        initAttackButtons();
    }

    private void initAttackButtons() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                final int x = i;
                final int y = j;

                JButton attackButton = new JButton();
                attackButton.setOpaque(false);
                attackButton.setContentAreaFilled(false);
                attackButton.setBorderPainted(false);
                attackButton.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                attackButton.addActionListener(evt -> onCellAttacked(x, y));

                add(attackButton);
            }
        }
    }

    private void onCellAttacked(int x, int y) {
        System.out.println("Attaque sur la case [" + x + ", " + y + "]");
        _controller.SendAttack(x, y);
    }
}