package models.placeable.boat;

public class AircraftCarrier extends Boat {
    public AircraftCarrier() {
        super("Aircraft Carrier", BoatType.AIRCRAFTCARRIER);
    }

    public AircraftCarrier(AircraftCarrier other) {
        super(other);
    }

    @Override
    public Boat clone() {
        return new AircraftCarrier(this);
    }
}
