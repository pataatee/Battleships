package models.player;

import models.grid.Grid;
import models.weapon.Missile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AIPlayerTest {

//    @Test
//    void randomAttackWithBomb() {
//        Grid g = new Grid(10);
//        Grid g2 =new Grid(10);
//        var p = new AIPlayer("Ai",0,g);
//        var p2 = new AIPlayer("Ai",0,g2);
//        p2.setWeapon(new Bomb());
//        var p2attack = p2.generateAttack();
//        p.getAttacked(p2attack);
//        int count =0;
//        for (int i = 0; i < g.getSize(); i++) {
//            for (int j = 0; j < g.getSize(); j++) {
//                if(!g.isTileFree(i,j)){
//                    count++;
//                };
//
//            }
//
//        }
//        assertEquals(5, count);
//    }


    @Test
    void randomAttack() {
        Grid g = new Grid(10);
        Grid g2 =new Grid(10);
        var p = new AIPlayer("Ai",0,g);
        var p2 = new AIPlayer("Ai",0,g2);
        p2.setWeapon(new Missile());
        var p2attack = p2.generateAttack();
        p.getAttacked(p2attack);
        int count =0;
        for (int i = 0; i < g.getSize(); i++) {
            for (int j = 0; j < g.getSize(); j++) {
                if(!g.isTileFree(i,j)){
                    count++;
                };

            }

        }
        assertEquals(1, count);
    }



}