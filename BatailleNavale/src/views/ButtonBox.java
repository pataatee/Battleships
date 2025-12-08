package views;

import javax.swing.*;
import java.awt.*;

public class ButtonBox extends JPanel {

    private JLabel _desc;
    private ImageButton _btnImg;
    private Boolean _layer;
    private JPanel _pan;

    public ButtonBox(ImageButton btn, String text) {
        this._btnImg = btn;
        this._desc = new JLabel(text, SwingConstants.CENTER);
        this._layer = false;

        this.initButtonBox();
        // this.createGrayOverlay();
    }

    public void initButtonBox() {

        // un petit panel dans lequel tout est bien rangé en boxLayout
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        this._btnImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._desc.setForeground(new Color(0));
        box.add(this._btnImg);
        box.add(this._desc);

        // le panel principal en OverlayLayout pour pouvoir superposer les panels (pour effet grisé)
        this.setLayout(new OverlayLayout(this));
        this.add(box);


    }

    public ImageButton getBtnImg() {
        return this._btnImg;
    }

    public void setLayer(Boolean layer) {
        this._layer = layer;
        this._pan.setVisible(layer);
    }

    public void setActivatedLabel(Boolean layer) {
        this._layer=layer;
        if (this._layer) {
            this._desc.setForeground(new Color(104, 16, 163));
            this._desc.setBackground(new Color(0xB6B8B8));
            this._desc.setOpaque(true);
        }
        else{
            this._desc.setOpaque(false);
            this._desc.setForeground(new Color(0));
        }
    }


    /*public void createGrayOverlay() {
        this._pan = new JPanel();
        this._pan.setBackground(Color.lightGray);
        this._pan.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._pan.setAlignmentY(Component.CENTER_ALIGNMENT);
        this._pan.setVisible(false);
        this._pan.setOpaque(true);
        this.add(this._pan);
    }*/


}
