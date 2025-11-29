package models.placeable.trap;

public class Tornado extends Trap {

    public Tornado(int ownerId) {
        super(ownerId, "Tornado", 1);
    }

    @Override
    public void effectOnHit() {
        //TODO: code tornado's effect
        super.setActivated(true);
    }
}
