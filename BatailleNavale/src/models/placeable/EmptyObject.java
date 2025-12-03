package models.placeable;

public class EmptyObject extends Placeable{
    public EmptyObject() {
        super("Empty Object", 1,PlaceableType.EMPTY);
    }

    @Override
    public void onHit() {
        //TODO: dunno if i have to write something here but feels weird to leave it empty
    }
}
