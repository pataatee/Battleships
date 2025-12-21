package models.placeable.boat;

public class TopedoBoat extends Boat {

    public TopedoBoat() {
        super("Torpedo Boat", BoatType.TORPEDOBOAT);
    }

    public TopedoBoat(TopedoBoat other) {
        super(other);
    }

    @Override
    public Boat clone() {
        return new TopedoBoat(this);
    }
}
