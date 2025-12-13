package models.grid;

import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.ShotResult;
import models.player.ShotResultType;
import models.weapon.Weapon;

import static models.player.ShotResultType.ISLANDHIT;

public class IslandTile extends Tile {

    Weapon _weapon;
    Trap _trap;

    public IslandTile() {
        super(TileState.ISLAND);
    }

    @Override
    public ShotResult onHit(int x ,int y) {
        super.setState(TileState.ISLANDHIT);
        return new ShotResult(x,y, ShotResultType.ISLANDHIT);
    }

    public void addWeapon(Weapon weapon){
        _weapon = weapon;
    }

    public void addTrap(Trap trap){
        _trap = trap;
    }

    public Trap getTrap() {
        return _trap;
    }

    public Weapon getWeapon() {
        return _weapon;
    }
}
