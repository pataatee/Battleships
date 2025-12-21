package views;

import controllers.LogsController;
import models.game.logs.Log;
import models.game.logs.LogsObserver;
import models.player.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LogsPanel extends JPanel implements LogsObserver {

    private ArrayList<JLabel> _lblLogs;
    private LogsController _lc;
    private JPanel _pnlForScrPan;
    private JScrollPane _scrPanLogs;

    public LogsPanel(LogsController lc) {

        // init scroll panels
        this._pnlForScrPan = new JPanel();
        this._pnlForScrPan.setLayout(new BoxLayout(this._pnlForScrPan, BoxLayout.Y_AXIS));
        this._pnlForScrPan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this._scrPanLogs = new JScrollPane(this._pnlForScrPan);
        this._scrPanLogs.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 5));
        this._scrPanLogs.setPreferredSize(new Dimension(100, 100));
        this._scrPanLogs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // init this
        this.setLayout(new BorderLayout());
        this.add(this._scrPanLogs, BorderLayout.CENTER);

        // init attributes
        this._lblLogs = new ArrayList<>();

        // init controller
        this._lc = lc;
        this._lc.addObserver(this);

    }


    @Override
    public void updateOnLogAdded(Log log) {

        JLabel lbl = new JLabel();
        if (log.getPlayer() == null) {
            lbl.setText("<html>" + "[Game]: " + log.getLogDesc() + "</html>");
            lbl.setForeground(new Color(0));
        }
        else {
            lbl.setText("<html>" + "[Player ID]: " + log.getPlayerId() + "<br>" + "[Player]: " + log.getPlayerName() + "<br>" + "[Action]: " + log.getLogDesc() + "</html>");

            if (log.getPlayerType() == PlayerType.AI) {
                lbl.setForeground(new Color(0x43066C));
            }
            else if (log.getPlayerType() == PlayerType.HUMAN) {
                lbl.setForeground(new Color(0x2D6045));
            }
        }
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        this._lblLogs.add(lbl);

        this.showLogs();

    }

    public void showLogs() {

        this._pnlForScrPan.removeAll();
        this._pnlForScrPan.repaint();

        for (JLabel log : this._lblLogs) {
            this._pnlForScrPan.add(log);
        }

        this._pnlForScrPan.revalidate();
        this._pnlForScrPan.repaint();


        // to auto scroll vertically when new stuff is added (vertically)
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = this._scrPanLogs.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

    }

}
