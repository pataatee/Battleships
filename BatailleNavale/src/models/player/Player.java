package models.player;

import models.grid.Grid;
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
    private int _pv;
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
        _pv++;
        _boatList.add(boat);
    };
    public void addTrap(Trap trap){
        _trapList.add(trap);

    };
    public void reactToDeathOfBoat(boolean live){
        _pv--;
        if(_pv<=0){
            _isAlive=false;
            notifyDeath();
        }
    }

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
                System.out.println(res[i]);
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



    public void notifyDeath(){

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
}
