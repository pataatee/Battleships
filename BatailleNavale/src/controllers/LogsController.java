package controllers;

import models.game.logs.Log;
import models.game.logs.LogsObserver;

import java.util.ArrayList;

public class LogsController implements LogsObserver {

    private ArrayList<LogsObserver> _observers;

    public LogsController() {

        this._observers = new ArrayList<>();

    }

    public void notifyObservers(Log log) {
        for (LogsObserver obs : this._observers) {
            obs.updateOnLogAdded(log);
        }
    }

    public void addObserver(LogsObserver obs) {
        this._observers.add(obs);
    }

    @Override
    public void updateOnLogAdded(Log log) {
        this.notifyObservers(log);
    }

}
