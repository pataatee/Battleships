import controllers.ConfigController;
import models.game.placement.ConfigData;
import models.game.placement.Placement;
import models.game.placement.RandomPlacementStrategy;
import models.game.placement.StaticPlacementStrategy;
import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import views.ConfigPanel;
import views.MainView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

//        Grid[] grids = new Grid[2];
//        grids[0] = new Grid(5);
//        grids[1] = new Grid(5);
//
//        ConfigData conf = new ConfigData(grids);
//
//        ConfigController configController = new ConfigController(conf);
//
//
//        ConfigPanel configScreen = new ConfigPanel(configController);
//        MainView main = new MainView(grids[0]);
//        main.setConfig(configScreen);
//        main.setVisible(true);
//        main.displayConfig();



        Grid[] g = new Grid[1];
        g[0]=new Grid(10);
        var p = new Placement(new StaticPlacementStrategy());

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




        p.placeObject(pla,g[0]);
        int count =0;
        for (int i = 0; i < g[0].getSize(); i++) {
            for (int j = 0; j < g[0].getSize(); j++) {
                if(!g[0].isTileFree(i,j)){
                    count++;
                };

            }

        }
        System.out.println(count);

        MainView main = new MainView(g[0]);
        main.setVisible(true);


    }
}