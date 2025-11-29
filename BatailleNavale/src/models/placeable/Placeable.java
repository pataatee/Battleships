package models.placeable;

public abstract class Placeable {
    private int _size;
    private String _name;

    public Placeable(String name, int size) {
        this._name=name;
        this._size=size;
    }

    public abstract void onHit();
}
