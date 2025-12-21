package models.game.placement;

import models.placeable.boat.BoatType;

public enum StaticPositions {

    // Cruiser's default position
    CRUISERDEFAULTPOS(BoatType.CRUISER, 7, 6, Orientation.VERTICAL),
    // Submarine's default position
    SUBMARINEDEFAULTPOS(BoatType.SUBMARINE, 7, 2, Orientation.HORIZONTAL),
    // TorpedoBoat's default position
    TORPEDOBOATDEFAULTPOS(BoatType.TORPEDOBOAT, 5, 9, Orientation.HORIZONTAL),
    // AircraftCarrier's default position
    AIRCRAFTCARRIERDEFAULTPOS(BoatType.AIRCRAFTCARRIER, 1, 5, Orientation.HORIZONTAL),

    // Destroyer's default position
    DESTROYERDEFAULTPOS(BoatType.DESTROYER, 1, 1, Orientation.VERTICAL),





    // BlackHole's default position
    BLACKHOLEDEFAULTPOS("Black Hole", 6, 8),

    // Tornado's default position
    TORNADODEFAULTPOS("Tordano", 9, 1);


    private BoatType _boatType;
    private int _x;
    private int _y;
    private Orientation _orientation;
    private String _trapName;

    StaticPositions(BoatType type, int x, int y, Orientation or) {
        this._boatType = type;
        this._x = x;
        this._y = y;
        this._orientation = or;
    }

    StaticPositions(String name, int x, int y) {
        this._trapName = name;
        this._x = x;
        this._y = y;
    }

    public BoatType getBoatType() {
        return this._boatType;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public Orientation getOrientation() {
        return this._orientation;
    }

    public String getTrapName() {
        return this._trapName;
    }
}
