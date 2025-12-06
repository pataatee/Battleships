package views;

import javax.swing.*;
import java.awt.*;

public class ButtonBox extends JPanel {

    private JLabel _desc;
    private ImageButton _btnImg;

    public ButtonBox(ImageButton btn, String text) {
        this._btnImg = btn;
        this._desc = new JLabel(text, SwingConstants.CENTER);

        this.initButtonBox();
    }

    public void initButtonBox() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this._btnImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this._btnImg);
        this.add(this._desc);

    }

    public ImageButton getBtnImg() {
        return this._btnImg;
    }

}
