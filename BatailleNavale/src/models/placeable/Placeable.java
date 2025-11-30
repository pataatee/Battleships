package models.placeable;

public abstract class Placeable {
    private int _size;
    private String _name;
    private PlaceableType _type;

    public Placeable(String name, int size, PlaceableType type) {
        this._name=name;
        this._size=size;
        this._type = type;
    }

    public abstract void onHit();

    public int getSize() {
        return this._size;
    }

    public String getName() {
        return this._name;
    }

    public PlaceableType getPlaceableType() {
        return this._type;
    }
}
