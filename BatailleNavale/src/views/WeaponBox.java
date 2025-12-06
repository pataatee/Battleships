package views;

import javax.swing.*;
import java.awt.*;

public class WeaponBox extends JPanel {

    private JLabel _desc;
    private ImageButton _btnWeapon;

    public WeaponBox(ImageButton btn, String text) {
        this._btnWeapon = btn;
        this._desc = new JLabel(text, SwingConstants.CENTER);

        this.initWeaponBox();
    }

    public void initWeaponBox() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this._btnWeapon.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this._btnWeapon);
        this.add(this._desc);

    }

    public ImageButton getBtnWeapon() {
        return this._btnWeapon;
    }

}
