package views;

import models.player.Stats;
import models.player.StatsObserver;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel implements StatsObserver {

    private JLabel _lblNbIntactBoats;
    private JLabel _lblNbHitBoats;
    private JLabel _lblNbSunkBoats;
    private JLabel _lblNbShotsMissed;
    private JLabel _lblNbBoatTilesHit;
    private JLabel _lblNbBoatTilesLeft;

    private JPanel _pnlUsedItems;
    private JPanel _pnlAvailableItems;

    private JLabel _lblNbIslandTilesLeft;

    public StatsPanel() {

        this.setLayout(new BorderLayout());

        JPanel pnlStats = new JPanel(new GridLayout(0,1));

        this._lblNbIntactBoats = new JLabel("Enemy's Intact Boats: 0");
        this._lblNbHitBoats = new JLabel("Enemy's Hit Boats: 0");
        this._lblNbSunkBoats = new JLabel("Enemy's Sunk Boats: 0");
        this._lblNbShotsMissed = new JLabel("Missed Shots: 0");
        this._lblNbBoatTilesHit = new JLabel("Boat Tiles Hit: 0");
        this._lblNbBoatTilesLeft = new JLabel("Boat Tiles Left: 0");
        this._lblNbIslandTilesLeft = new JLabel("Island Tiles Left: 16");

        pnlStats.add(this._lblNbIntactBoats);
        pnlStats.add(this._lblNbHitBoats);
        pnlStats.add(this._lblNbSunkBoats);
        pnlStats.add(this._lblNbShotsMissed);
        pnlStats.add(this._lblNbBoatTilesHit);
        pnlStats.add(this._lblNbBoatTilesLeft);
        pnlStats.add(this._lblNbIslandTilesLeft);

        this.add(pnlStats, BorderLayout.NORTH);

        this._pnlUsedItems = new JPanel();
        this._pnlUsedItems.setLayout(new BoxLayout(this._pnlUsedItems, BoxLayout.Y_AXIS));
        this._pnlUsedItems.setBorder(BorderFactory.createTitledBorder("Used items"));

        this._pnlAvailableItems = new JPanel();
        this._pnlAvailableItems.setLayout(new BoxLayout(this._pnlAvailableItems, BoxLayout.Y_AXIS));
        this._pnlAvailableItems.setBorder(BorderFactory.createTitledBorder("Available items"));

        JPanel pnlItems = new JPanel(new GridLayout(2,1));
        pnlItems.add(this._pnlAvailableItems);
        pnlItems.add(this._pnlUsedItems);

        this.add(pnlItems, BorderLayout.CENTER);


    }


    @Override
    public void updateStats(Stats stats) {

        this._lblNbIntactBoats.setText("Enemy's Intact Boats: " + stats.getNbIntactBoats());
        this._lblNbHitBoats.setText("Enemy's Hit Boats: " + stats.getNbHitBoats());
        this._lblNbSunkBoats.setText("Enemy's Sunk Boats: " + stats.getNbSunkBoats());
        this._lblNbShotsMissed.setText("Missed Shots: " + stats.getNbMissedShots());
        this._lblNbBoatTilesHit.setText("Boat Tiles Hit: " + stats.getNbHitBoatTiles());
        this._lblNbBoatTilesLeft.setText("Boat Tiles Left: " + stats.getNbBoatTilesLeft());
        this._lblNbIslandTilesLeft.setText("Island Tiles Left: " + stats.getNbIslandTilesLeft());

        this._pnlAvailableItems.removeAll();
        for (String item : stats.getAvailableItems()) {
            this._pnlAvailableItems.add(new JLabel(item));
        }

        this._pnlUsedItems.removeAll();
        for (String item : stats.getUsedItems()) {
            this._pnlUsedItems.add(new JLabel(item));
        }

        this._pnlAvailableItems.revalidate();
        this._pnlAvailableItems.repaint();
        this._pnlUsedItems.revalidate();
        this._pnlUsedItems.repaint();

    }

}
