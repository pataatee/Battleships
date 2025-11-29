package models.placeable.trap;

public class BlackHole extends Trap {

    public BlackHole(int ownerId) {
        super(ownerId, "Black Hole", 1);
    }

    @Override
    public void effectOnHit() {
        //TODO: code BlackHole's effect
        super.setActivated(true);
    }
}
