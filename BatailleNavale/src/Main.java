import controllers.GameController;
import controllers.PlacementController;
import controllers.WeaponController;
import models.game.Game;
import models.game.GameMode;
import models.game.placement.*;
import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import models.player.AIPlayer;
import models.player.HumanPlayer;
import models.player.Player;
import models.weapon.Bomb;
import views.GridPanel;
import views.MainView;
import views.WeaponPanel;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Grid[] g = new Grid[2];
        g[0] = new Grid(10);
        g[1] = new Grid(10);
        Player p2 = new AIPlayer("Ai", 1, g[0]);
        Player p1 = new HumanPlayer("human", 0, g[1]);
        Game game = new Game(p1, p2);
        game.setUpGameMode(GameMode.ISLAND);
        g[0].addWeaponToIslandTile(4,4,new Bomb());
        var p = new Placement(new ManualPlacementStrategy());
        var fac = new PlaceableFactory();

        var pla1 = new Placeable[10];
        pla1[0] = fac.createTorpedoBoat();
        pla1[1] = fac.createAircraftCarrier();
        pla1[2] = fac.createCruiser();
        pla1[3] = fac.createDestroyer();
        pla1[4] = fac.createSubmarine();
        pla1[5] = fac.createAircraftCarrier();
        pla1[6] = fac.createTorpedoBoat();
        pla1[7] = fac.createBlackHole(0);
        pla1[8] = fac.createTornado(0);
        pla1[9] = fac.createTorpedoBoat();
//
//        var pla2 = new Placeable[10];
//        pla2[0] = fac.createTorpedoBoat();
//        pla2[1] = fac.createAircraftCarrier();
//        pla2[2] = fac.createCruiser();
//        pla2[3] = fac.createDestroyer();
//        pla2[4] = fac.createSubmarine();
//        pla2[5] = fac.createAircraftCarrier();
//        pla2[6] = fac.createTorpedoBoat();
//        pla2[7] = fac.createBlackHole(0);
//        pla2[8] = fac.createTornado(0);
//        pla2[9] = fac.createTorpedoBoat();

        //p.setPlacementStrategy(new RandomPlacementStrategy());
//        p.placeObject(pla1, g[0], null);
//        p.placeObject(pla2, g[1], null);
//        p1.setWeapon(new Bomb());
//        p2.addPlaceable(pla1);
//        p1.addPlaceable(pla2);

        GameController gc = new GameController(game);

        GridPanel[] panels = new GridPanel[2];
        panels[0] = new GridPanel(gc, g[0], false);
        panels[1] = new GridPanel(gc, g[1], true);

        g[0].addObserver(panels[0]);
        g[1].addObserver(panels[1]);
        game.startGame();
        WeaponController wc = new WeaponController(p1);

        PlacementController pc = new PlacementController(p, pla1, new Coord(0, 0, Orientation.HORIZONTAL),g[1], game.getGameMode());

        MainView main = new MainView(panels,wc, pc, gc, g[1]);
        game.addObserver(main);
        main.setVisible(true);
    }
}