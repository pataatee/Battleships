package models.player;

import models.grid.Grid;
import models.grid.TileState;
import models.placeable.Placeable;
import models.placeable.PlaceableType;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.*;

import java.util.ArrayList;

public abstract class Player{
    private int _id;
    private String _name;
    private boolean _isAlive=true;
    private ArrayList<Boat> _boatList;
    private ArrayList<Trap> _trapList;
    private Grid _grid;
    private Weapon _currentWeapon;
    private ArrayList<WeaponType> _WeaponList;
    private PlayerType _type;
    private ArrayList<WeaponObserver> _wpObserverList;



    public Player(String name,int id,Grid grid,PlayerType type){
        _id=id;
        _name=name;
        _grid= grid;
        _boatList = new ArrayList<Boat>();
        _trapList = new ArrayList<Trap>();
        _currentWeapon=new Missile();
        _WeaponList = new ArrayList<>();
        _type = type;
        _wpObserverList= new ArrayList<>();
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

    public ShotResult[] getAttacked(Attack attack){
        int x = attack.getX();
        int y =  attack.getY();
        Weapon weapon = attack.getWeapon();
        Effect[] effect = weapon.use(x,y);
        ShotResult[] res = new ShotResult[effect.length];
        int i = 0 ;
        for (Effect value : effect) {
            if (value.getEffectType() == EffectType.HIT) {
                res[i] = _grid.hitTile(value.getPos()[0], value.getPos()[1]);
                if(res[i].get_type()==ShotResultType.SUNK){
                    reactToSunk();
                }
            } else {
                continue;
            }
            i++;
        }
        return res;
    }



    public void setWeapon(Weapon weapon){
        if(_WeaponList.contains(weapon.get_type())){
            _currentWeapon = weapon;
            System.out.println("new weapon added"+weapon);

        }else {
            _currentWeapon = new Missile();
        }
        notifyWeaponSelected(_currentWeapon.get_type());
    }

    public void addWeapon(Weapon weapon){
        if(weapon ==null) return;
        _WeaponList.add(weapon.get_type());
        System.out.println(weapon);
        notifyWeaponUnlocked(weapon.get_type(),true);
    }

    public void removeWeapon(Weapon weapon){
        _WeaponList.remove(weapon.get_type());
        notifyWeaponUnlocked(weapon.get_type(),false);
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

    public void setUpIsland() {
        _grid.setUpIsland();
    }

    public Grid getGrid(){
        return _grid;
    }


    public void addWeaponObserver(WeaponObserver wo){
        _wpObserverList.add(wo);
    }

    public void notifyWeaponSelected(WeaponType wp){
        for(WeaponObserver wo : _wpObserverList){
            wo.notifySelected(wp);
        }
    }

    public void notifyWeaponUnlocked(WeaponType wp,boolean unlock){
        for(WeaponObserver wo : _wpObserverList){
            wo.notifyUnlocked(wp,unlock);
        }
    }


}
