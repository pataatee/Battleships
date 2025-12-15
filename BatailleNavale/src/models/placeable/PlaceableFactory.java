package models.placeable;

import models.placeable.boat.*;
import models.placeable.trap.BlackHole;
import models.placeable.trap.Tornado;
import models.placeable.trap.Trap;

public class PlaceableFactory {

    public PlaceableFactory() { }

    public Boat createCruiser() {
        return new Cruiser();
    }

    public Boat createDestroyer() {
        return new Destroyer();
    }

    public Boat createAircraftCarrier() {
        return new AircraftCarrier();
    }

    public Boat createSubmarine() {
        return new Submarine();
    }

    public Boat createTorpedoBoat() {
        return new TopedoBoat();
    }

    public Trap createBlackHole(int ownerId) {
        return new BlackHole(ownerId);
    }

    public Trap createTornado(int ownerId) {
        return new Tornado(ownerId);
    }

    public Placeable createEmptyObject() { return new EmptyObject(); }
}
