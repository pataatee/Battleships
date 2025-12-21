package models.placeable.boat;

public class Submarine extends Boat {
    public Submarine() {
        super("Submarine", BoatType.SUBMARINE);
    }

    public Submarine(Submarine other) {
        super(other);
    }

    @Override
    public Boat clone() {
        return new Submarine(this);
    }
}
