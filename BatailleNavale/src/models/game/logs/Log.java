package models.game.logs;

import models.player.PlayerType;

public class Log {

    private final int playerId;
    private final String playerName;
    private final PlayerType playerType;
    private final String logDesc;

    public Log(int playerId, String playerName, PlayerType playerType, String desc) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerType = playerType;
        this.logDesc = desc;
    }

    public Log(String desc) {
        this.playerId = -1;
        this.playerName = null;
        this.playerType = null;
        this.logDesc = desc;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getLogDesc() {
        return logDesc;
    }
}
