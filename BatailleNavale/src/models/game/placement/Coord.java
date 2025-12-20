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

    public void setOrientation(Orientation or) {
        this._or = or;
    }

    public void setX(int x) {
        this._x = x;
    }

    public void setY(int y) {
        this._y = y;
    }

    public String toString() {
        return "x: " + this._x + " y: " + this._y + " Orientation: " + this._or;
    }
}
