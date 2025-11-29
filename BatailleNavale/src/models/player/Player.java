package models.player;

import models.grid.Grid;
import models.placeable.boat.Boat;
import models.placeable.trap.Trap;
import models.weapon.Effect;
import models.weapon.Weapon;

import java.util.ArrayList;

public abstract class Player {
    private int _id;
    private String _name;
    private boolean _isAlive=true;
    private ArrayList<Boat> _boatList;
    private ArrayList<Trap> _trapList;
    private int _pv;
    private ArrayList<PlayerObserver> _lstObserver;
    private Grid _grid;



    public Player(String name,int id,Grid grid){
        _id=id;
        _name=name;
        _grid= grid;
        _boatList = new ArrayList<Boat>();
        _trapList = new ArrayList<Trap>();
        _lstObserver = new ArrayList<PlayerObserver>();

    }

    public abstract void playerTurn();

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

    public void attack(int x, int y, Weapon weapon){
        for (PlayerObserver playerObserver : _lstObserver) {
            playerObserver.notifyShoot(x, y, weapon);
        }
    }

    public void getAttacked(int x, int y, Weapon weapon){
        Effect[] effect = weapon.use(x,y);
        ShortResult[] res = new ShortResult[];
        for (int i = 0; i <effect.length ; i++) {
            if(effect[i].getDammage()>0){
                _grid.hitTile(x,y);
            }else{
                continue;
            }
        }
        for (PlayerObserver playerObserver : _lstObserver) {
            playerObserver.notifyHit();
        }
    }



    public void notifyDeath(){

    }




}
