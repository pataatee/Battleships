package models.grid;

import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.player.ShotResult;
import models.player.ShotResultType;
import models.weapon.WeaponType;
import models.weapon.Weapon;

import static models.player.ShotResultType.ISLANDHIT;

public class IslandTile extends Tile {

    WeaponType _weapon;
    Weapon _weapon;
    Trap _trap;

    public IslandTile() {
        super(TileState.ISLAND);
        _weapon =null;
    }

    @Override
    public ShotResult onHit(int x ,int y) {
        super.setState(TileState.ISLANDHIT);
        if(_weapon == null){
            return new ShotResult(x,y, ShotResultType.ISLANDHIT);
        }
        switch (_weapon){
            case BOMB -> {
                return new ShotResult(x,y,ShotResultType.DISCOVERBOMB);
            }
            case SONAR -> {
                return new ShotResult(x,y,ShotResultType.DISCOVERSONAR);
            }
        }
        return new ShotResult(x,y, ShotResultType.ISLANDHIT);
    }

    public void addWeapon(WeaponType weapon){
        _weapon = weapon;
    }

    public WeaponType getWeapon() {
        return _weapon;
    }
}
