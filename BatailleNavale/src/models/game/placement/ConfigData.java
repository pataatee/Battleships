package models.game.placement;

import controllers.GameController;
import models.game.GameMode;
import models.placeable.boat.Boat;
import models.placeable.boat.BoatType;
import models.player.Player;

import java.util.ArrayList;

public class ConfigData {

    private static final int MAXCELL = 35;

    private int gridSize;
    private int sumCell;
    private GameMode gameMode;

    private final Player _player1;
    private final Player _player2;

    private final ArrayList<Boat> boatList;

    public ConfigData(Player player1, Player player2) {
        this._player1 = player1;
        this._player2 = player2;
        this.sumCell = 0;
        this.gameMode = GameMode.NORMAL;
        this.boatList = new ArrayList<>();
    }


    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;

        _player1.getGrid().setSize(gridSize);
        _player2.getGrid().setSize(gridSize);
    }

    public boolean addBoat(Boat boat) {
        int newTotal = sumCell + boat.getSize();

        if (newTotal > MAXCELL) {
            return false;
        }

        sumCell = newTotal;
        boatList.add(boat);
        return true;
    }

    public boolean removeBoat(BoatType boatType) {
        for (int i = boatList.size() - 1; i >= 0; i--) {
            Boat boat = boatList.get(i);
            if (boat.getType() == boatType) {
                boatList.remove(i);
                sumCell -= boat.getSize();
                return true;
            }
        }
        return false;
    }

    public int getTotalCells() {
        return sumCell;
    }

    public int getBoatCountByType(BoatType boatType) {
        int count = 0;
        for (Boat boat : boatList) {
            if (boat.getType() == boatType) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Boat> getChosenBoats() {
        return boatList;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
