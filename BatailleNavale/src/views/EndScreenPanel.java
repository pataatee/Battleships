package views;

import controllers.GameController;
import models.game.GameState;
import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class EndScreenPanel extends JPanel {

    public EndScreenPanel(Player winner, GameController gameController) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel winnerLabel = new JLabel(winner.getName() + " a gagné, bravo 🎊");
        winnerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        winnerLabel.setForeground(Color.WHITE);

        gbc.gridy = 1;
        add(winnerLabel, gbc);

        JButton resetButton = new JButton("Rejouer");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e ->
                gameController.setState(GameState.CONFIG)
        );

        gbc.gridy = 2;
        add(resetButton, gbc);
    }
}
