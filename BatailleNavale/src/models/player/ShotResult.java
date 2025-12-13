package models.player;

public class ShotResult {

    int _x;
    int _y;
    ShotResultType _type;

    public ShotResult(int x, int y, ShotResultType type) {
        _x = x;
        _y = y;
        _type = type;
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public ShotResultType get_type() {
        return _type;
    }
}
