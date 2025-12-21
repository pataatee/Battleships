package models.placeable.boat;

public class Destroyer extends Boat {
    public Destroyer() {
        super("Destroyer", BoatType.DESTROYER);
    }

    public Destroyer(Destroyer other) {
        super(other);
    }

    @Override
    public Boat clone() {
        return new Destroyer(this);
    }
}
