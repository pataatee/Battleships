package models.placeable;

import models.placeable.boat.*;
import models.placeable.trap.*;

public class PlaceableFactory {

    public PlaceableFactory() { }

    public Boat createCruiser() {
        return new Cruiser();
    }

    public Boat createDestroyer() {
        return new Destroyer();
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
}
