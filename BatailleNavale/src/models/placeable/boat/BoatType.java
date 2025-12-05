package models.placeable.boat;

public enum BoatType {
    AIRCRAFTCARRIER(5),
    CRUISER(4),
    DESTROYER(3),
    SUBMARINE(3),
    TORPEDOBOAT(2);

    private int _size;

    BoatType(int size) {
        this._size=size;
    }

    public int getSize() {
        return this._size;
    }
}
