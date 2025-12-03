package models.player;

import models.grid.Grid;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.Effect;
import models.weapon.EffectType;
import models.weapon.Missile;
import models.weapon.Weapon;

import java.util.ArrayList;

public abstract class Player {
    private int _id;
    private String _name;
    private boolean _isAlive=true;
    private ArrayList<Boat> _boatList;
    private ArrayList<Trap> _trapList;
    private int _pv;
    private Grid _grid;
    private Weapon _currentWeapon;


    public Player(String name,int id,Grid grid){
        _id=id;
        _name=name;
        _grid= grid;
        _boatList = new ArrayList<Boat>();
        _trapList = new ArrayList<Trap>();
        _currentWeapon=new Missile();
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

    public abstract Attack attack(int x, int y, Weapon weapon);

    public void getAttacked(Attack attack){
        int x = attack.getX();
        int y =  attack.getY();
        Weapon weapon = attack.getWeapon();
        Effect[] effect = weapon.use(x,y);
        System.out.println(weapon);
        System.out.println(effect.length);
        ShotResult res = new ShotResult();
        for (Effect value : effect) {
            if (value.getEffectType() == EffectType.HIT) {
                _grid.hitTile(value.getPos()[0], value.getPos()[1]);
            } else {
                continue;
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



}
