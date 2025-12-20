package views;

import models.player.Player;

import javax.swing.*;
import java.awt.*;

public class EndScreenPanel extends JPanel {

    public EndScreenPanel(Player winner) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;
        gbc.gridx = 0;
        gbc.gridy = y;
        this.add(new JLabel(winner.toString()), gbc);
        System.out.println("winner" + winner);
    }

}
