package views;

import javax.swing.*;
import java.awt.*;

public class ButtonGridPanel extends JPanel {

    private final int _gridSize;
    private GridButtonDelegate _delegate;

    public ButtonGridPanel(int gridSize, GridButtonDelegate delegate) {
        this._gridSize = gridSize;
        this._delegate = delegate;

        setOpaque(false);
        setLayout(new GridLayout(gridSize, gridSize, 0, 0));

        initButtons();
    }

    private void initButtons() {
        for (int x = 0; x < _gridSize; x++) {
            for (int y = 0; y < _gridSize; y++) {

                JButton button = new JButton();
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);

                int finalX = x;
                int finalY = y;
                button.addActionListener(e -> _delegate.invoke(finalX, finalY));

                add(button);
            }
        }
    }

    public void set_delegate(GridButtonDelegate _delegate) {
        this._delegate = _delegate;
    }
}
