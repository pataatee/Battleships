package views;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {
    // to create the weapon buttons w images etc

    private int _btnWidth;
    private int _btnHeight;
    private String _imgPath;

    public ImageButton(int width, int height, String path) {

        this._btnHeight = height;
        this._btnWidth = width;
        this._imgPath = path;
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setRolloverEnabled(false);
        this.initializeButton();

    }

    public void initializeButton() {
        // creating the button's icon w a great size
        ImageIcon icon = new ImageIcon(this._imgPath);
        Image img = icon.getImage();
        img = img.getScaledInstance(this._btnWidth, this._btnHeight, Image.SCALE_SMOOTH); // scale_smooth bcs why not
        ImageIcon newIcon = new ImageIcon(img);
        // sets the button's size (same size as the pic's one)
        this.setPreferredSize(new Dimension(this._btnWidth, this._btnHeight));
        // adds the icon to the button :D
        this.setIcon(newIcon);
    }


}
