package models.placeable.boat;

public class Cruiser extends Boat {
    public Cruiser() {
        super("Cruiser", BoatType.CRUISER);
    }

    public Cruiser(Cruiser other) {
        super(other);
    }

    @Override
    public Boat clone() {
        return new Cruiser(this);
    }
}
