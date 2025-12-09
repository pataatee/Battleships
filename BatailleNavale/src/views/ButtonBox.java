package views;

import javax.swing.*;
import java.awt.*;

public class ButtonBox extends JPanel {

    private JLabel _desc;
    private ImageButton _btnImg;
    private JPanel _pan;

    public ButtonBox(ImageButton btn, String text) {
        this._btnImg = btn;
        this._desc = new JLabel(text, SwingConstants.CENTER);

        this.initButtonBox();
        // this.createGrayOverlay();
    }

    public void initButtonBox() {

        // un petit panel dans lequel tout est bien rangé en boxLayout
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        this._btnImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(this._btnImg);
        box.add(this._desc);

        // le panel gris au-dessus
        this._pan = new JPanel();
        this._pan.setBackground(new Color(255, 255, 255,150));
        this._pan.setAlignmentX(Component.CENTER_ALIGNMENT);
        this._pan.setAlignmentY(Component.CENTER_ALIGNMENT);
        this._pan.setVisible(false);
        this._pan.setOpaque(true);

        // le panel principal en OverlayLayout pour pouvoir superposer les panels (pour effet grisé)
        this.setLayout(new OverlayLayout(this));
        this.add(_pan);
        this.add(box);


    }

    public ImageButton getBtnImg() {
        return this._btnImg;
    }

    public void setSelectedLayer() {
        this._pan.setVisible(true);
        this._pan.setBackground(new Color(0x66E61313, true));
        this.setBackground(new Color(255,255,255));
        this._desc.setBackground(new Color(255,255,255));
        this._btnImg.setBackground(new Color(255,255,255));
        this.setForeground(new Color(255,255,255));
        this.updateUI();
    }

    public void setNotSelectedLayer() {
        this._pan.setVisible(true);
        this._pan.setBackground(new Color(0x86B5AEAE, true));
        this.setBackground(new Color(255,255,255));
        this._desc.setBackground(new Color(255,255,255));
        this._btnImg.setBackground(new Color(255,255,255));
        this.setForeground(new Color(255,255,255));
        this.updateUI();
    }



    /*public void setActivatedLabel(Boolean layer) {
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
    }*/


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
