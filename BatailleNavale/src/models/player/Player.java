package models.player;

import models.grid.Grid;
import models.grid.TileState;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.Effect;
import models.weapon.EffectType;
import models.weapon.Missile;
import models.weapon.Weapon;

import java.util.ArrayList;

public abstract class Player{
    private int _id;
    private String _name;
    private boolean _isAlive=true;
    private ArrayList<Boat> _boatList;
    private ArrayList<Trap> _trapList;
    private Grid _grid;
    private Weapon _currentWeapon;
    private PlayerType _type;



    public Player(String name,int id,Grid grid,PlayerType type){
        _id=id;
        _name=name;
        _grid= grid;
        _boatList = new ArrayList<Boat>();
        _trapList = new ArrayList<Trap>();
        _currentWeapon=new Missile();
        _type = type;
    }

    public void addBoat(Boat boat){
        _boatList.add(boat);
    };
    public void addTrap(Trap trap){
        _trapList.add(trap);

    };

    public Attack createAttack(int x, int y) {
        return new Attack(x, y, _currentWeapon);
    }

    public ShotResultType[] getAttacked(Attack attack){
        int x = attack.getX();
        int y =  attack.getY();
        Weapon weapon = attack.getWeapon();
        Effect[] effect = weapon.use(x,y);
        ShotResultType[] res = new ShotResultType[effect.length];
        int i = 0 ;
        for (Effect value : effect) {
            if (value.getEffectType() == EffectType.HIT) {
                res[i] = _grid.hitTile(value.getPos()[0], value.getPos()[1]);
                if(res[i]==ShotResultType.SUNK){
                    reactToSunk();
                }
            } else {
                continue;
            }
            i++;
        }
        return res;
    }

    public void handelShotResult(ShotResultType[] resultTypes){
        for(ShotResultType res : resultTypes){
            switch (res) {
                case MISS -> {
                    System.out.println("Add to log Miss");
                }
                case HIT -> {
                    System.out.println("Add to log Hit");
                }
                case SUNK -> {
                    System.out.println("Add to log Sunk");
                }
                case TORNAD -> {
                    System.out.println("Add to log Tornadoed");
                }
                case BLACKHOLE -> {
                    System.out.println("Add to log BlackHole");
                }
            }
        }
    }

    public void setWeapon(Weapon weapon){
        _currentWeapon = weapon;
    }

    public Weapon getWeapon(){
        return _currentWeapon;
    }


    public boolean isAlive() {
        return _isAlive;
    }

    public PlayerType getType() {
        return _type;
    }


    public void reactToSunk(){
        int i =0;
        for (Boat boat : _boatList) {
            if (boat.isDead()) {
                i++;
                int[][] positions = boat.getPosition();
                for (int[] pos : positions) {
                    _grid.changeStateOfTile(pos[0],pos[1], TileState.BOATDEAD);
                }
            }
        }
        if(i==_boatList.size()){
            _isAlive=false;
        }
    }


    public void addPlaceable(Placeable[] placeables) {
        for(Placeable pl : placeables){
            if(pl.getPlaceableType()== PlaceableType.BOAT){
                this.addBoat((Boat) pl);
            }
            if(pl.getPlaceableType()== PlaceableType.TRAP){
                this.addTrap((Trap) pl);
            }

        }

    }
}
