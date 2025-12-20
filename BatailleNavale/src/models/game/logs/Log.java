package models.game.logs;

import models.player.Player;
import models.player.PlayerType;

public class Log {

    private Player _player;
    private String _logDesc;

    public Log(Player pl, String desc) {

        this._player = pl;
        this._logDesc = desc;

    }

    public Log(String desc) {
        this._logDesc = desc;
    }

    public String getPlayerName() {
        if (this._player == null) {
            return null;
        }
        return this._player.getName();
    }

    public int getPlayerId() {
        if (this._player == null) {
            return -1;
        }
        return this._player.getId();
    }

    public String getLogDesc() {
        return this._logDesc;
    }

    public Player getPlayer() {
        return this._player;
    }

    public PlayerType getPlayerType() {
        return this._player.getType();
    }

}
