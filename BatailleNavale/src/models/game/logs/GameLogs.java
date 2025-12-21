package models.game.logs;

import java.util.ArrayList;

public class GameLogs {

    private ArrayList<Log> _gameLogs;
    private ArrayList<LogsObserver> _observers;

    public GameLogs() {

        this._gameLogs = new ArrayList<Log>();
        this._observers = new ArrayList<>();

    }

    public void addLog(Log log) {

        this._gameLogs.add(log);
        this.notifyObservers(log);

    }

    public ArrayList<Log> getLogs() {
        return this._gameLogs;
    }

    public void notifyObservers(Log log) {
        for (LogsObserver obs : this._observers) {
            obs.updateOnLogAdded(log);
        }
    }

    public void addObserver(LogsObserver obs) {
        this._observers.add(obs);
    }

}
