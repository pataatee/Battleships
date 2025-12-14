package models.game.placement;

public class Coord {
    private int _x;
    private int _y;
    private Orientation _or;

    public Coord(int x, int y, Orientation or) {
        this._x = x;
        this._y = y;
        this._or = or;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public Orientation getOrientation() {
        return this._or;
    }
}
