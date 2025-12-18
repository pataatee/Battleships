package models.game.placement;

import models.grid.Grid;
import models.placeable.Placeable;

public class StaticPlacementStrategy extends PlacementStrategy {


    public StaticPlacementStrategy() {
    }


    @Override
    public Boolean placeObjects(Placeable[] placeable, Grid grid, Coord co) {

        grid.placeObject(placeable[0], StaticPositions.AIRCRAFTCARRIERDEFAULTPOS.getX(), StaticPositions.AIRCRAFTCARRIERDEFAULTPOS.getY(), StaticPositions.AIRCRAFTCARRIERDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[1], StaticPositions.CRUISERDEFAULTPOS.getX(), StaticPositions.CRUISERDEFAULTPOS.getY(), StaticPositions.CRUISERDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[2], StaticPositions.DESTROYERDEFAULTPOS.getX(), StaticPositions.DESTROYERDEFAULTPOS.getY(), StaticPositions.DESTROYERDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[3], StaticPositions.SUBMARINEDEFAULTPOS.getX(), StaticPositions.SUBMARINEDEFAULTPOS.getY(), StaticPositions.SUBMARINEDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[4], StaticPositions.TORPEDOBOATDEFAULTPOS.getX(), StaticPositions.TORPEDOBOATDEFAULTPOS.getY(), StaticPositions.TORPEDOBOATDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[5], StaticPositions.BLACKHOLEDEFAULTPOS.getX(), StaticPositions.BLACKHOLEDEFAULTPOS.getY(), StaticPositions.BLACKHOLEDEFAULTPOS.getOrientation());
        grid.placeObject(placeable[6], StaticPositions.TORNADODEFAULTPOS.getX(), StaticPositions.TORNADODEFAULTPOS.getY(), StaticPositions.TORNADODEFAULTPOS.getOrientation());

        return true;

    }

    @Override
    public Boolean placeOneObject(Placeable placeable, Grid grid, Coord co) {
        return false;
    }


}
