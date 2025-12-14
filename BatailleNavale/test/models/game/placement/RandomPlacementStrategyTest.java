package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomPlacementStrategyTest {

    @Test
    void placeObjects() {
        Grid[] g = new Grid[1];
        g[0]=new Grid(10);
        var p = new  Placement(g,new RandomPlacementStrategy());
        
        var fac = new PlaceableFactory();
        var boat = fac.createTorpedoBoat();
        var pla = new Placeable[2];
        
        pla[0]=boat;
        pla[1]= fac.createAircraftCarrier();
        p.placeObject(pla,g[0], null);
        int count =0;
        for (int i = 0; i < g[0].getSize(); i++) {
            for (int j = 0; j < g[0].getSize(); j++) {
                if(!g[0].isTileFree(i,j)){
                    count++;
                };

            }
            
        }
        assertEquals(7, count);
    }

    @Test
    void placeMoreObjects() {
        Grid[] g = new Grid[1];
        g[0]=new Grid(10);
        var p = new  Placement(g,new RandomPlacementStrategy());

        var fac = new PlaceableFactory();
        var boat = fac.createTorpedoBoat();
        var boat2 = fac.createAircraftCarrier();
        var boat3 = fac.createCruiser();
        var boat4 = fac.createDestroyer();
        var boat5 = fac.createSubmarine();
        var boat6 = fac.createAircraftCarrier();
        var boat7 = fac.createTorpedoBoat();
        var boat8 = fac.createBlackHole(0);
        var boat9 = fac.createTornado(0);
        var boat10 = fac.createTorpedoBoat();
        var pla = new Placeable[10];

        pla[0]=boat;
        pla[1]=boat2;
        pla[2]=boat3;
        pla[3]=boat4;
        pla[4]=boat5;
        pla[5]=boat6;
        pla[6]=boat7;
        pla[7]=boat8;
        pla[8]=boat9;
        pla[9]=boat10;




        p.placeObject(pla,g[0], null);
        int count =0;
        for (int i = 0; i < g[0].getSize(); i++) {
            for (int j = 0; j < g[0].getSize(); j++) {
                if(!g[0].isTileFree(i,j)){
                    count++;
                };

            }

        }
        assertEquals(count,28);
    }
}