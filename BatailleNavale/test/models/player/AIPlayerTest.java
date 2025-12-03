package models.player;

import models.game.placement.Placement;
import models.game.placement.RandomPlacementStrategy;
import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import models.weapon.Bomb;
import models.weapon.Missile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

    @Test
    void randomAttackWithBomb() {
        Grid g = new Grid(10);
        Grid g2 =new Grid(10);
        var p = new AIPlayer("Ai",0,g);
        var p2 = new AIPlayer("Ai",0,g2);
        p2.setWeapon(new Bomb());
        var p2attack = p2.randomAttack();
        p.getAttacked(p2attack);
        int count =0;
        for (int i = 0; i < g.getSize(); i++) {
            for (int j = 0; j < g.getSize(); j++) {
                System.out.println(g.isTileFree(i,j));
                if(!g.isTileFree(i,j)){
                    count++;
                };

            }

        }
        assertEquals(5, count);
    }


    @Test
    void randomAttack() {
        Grid g = new Grid(10);
        Grid g2 =new Grid(10);
        var p = new AIPlayer("Ai",0,g);
        var p2 = new AIPlayer("Ai",0,g2);
        p2.setWeapon(new Missile());
        var p2attack = p2.randomAttack();
        p.getAttacked(p2attack);
        int count =0;
        for (int i = 0; i < g.getSize(); i++) {
            for (int j = 0; j < g.getSize(); j++) {
                System.out.println(g.isTileFree(i,j));
                if(!g.isTileFree(i,j)){
                    count++;
                };

            }

        }
        assertEquals(1, count);
    }



}