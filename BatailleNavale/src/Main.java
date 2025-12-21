import controllers.GameController;
import controllers.LogsController;
import controllers.WeaponController;
import models.game.Game;
import models.game.GameMode;
import models.game.placement.Placement;
import models.game.placement.RandomPlacementStrategy;
import models.game.placement.StaticPlacementStrategy;
import models.grid.Grid;
import models.placeable.Placeable;
import models.placeable.PlaceableFactory;
import models.player.AIPlayer;
import models.player.HumanPlayer;
import models.player.Player;
import models.weapon.WeaponType;
import views.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Grid[] g = new Grid[2];
        g[0] = new Grid(10);
        g[1] = new Grid(10);

        Player p1 = new HumanPlayer("human", 0, g[0]);
        Player p2 = new AIPlayer("Ai", 1, g[1]);

        Game game = new Game(p1, p2);


        var p = new Placement(new RandomPlacementStrategy());
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

        var pla2 = new Placeable[10];
        pla2[0] = fac.createTorpedoBoat();
        pla2[1] = fac.createAircraftCarrier();
        pla2[2] = fac.createCruiser();
        pla2[3] = fac.createDestroyer();
        pla2[4] = fac.createSubmarine();
        pla2[5] = fac.createAircraftCarrier();
        pla2[6] = fac.createTorpedoBoat();
        pla2[7] = fac.createBlackHole(0);
        pla2[8] = fac.createTornado(0);
        pla2[9] = fac.createTorpedoBoat();


        //p.setPlacementStrategy(new RandomPlacementStrategy());
//        p.placeObject(pla1, g[0], null);
//        p.placeObject(pla2, g[1], null);
//        p1.setWeapon(new Bomb());
//        p2.addPlaceable(pla1);
//        p1.addPlaceable(pla2);

//        p1.addPlaceable(pla1);
//        p2.addPlaceable(pla2);

        GameController gc = new GameController(game);

        WeaponController wc1 = new WeaponController(p1);
        WeaponPanel wp1 = new WeaponPanel(wc1);
        p1.addWeaponObserver(wp1);

        WeaponController wc2 = new WeaponController(p2);
        WeaponPanel wp2 = new WeaponPanel(wc2);
        p2.addWeaponObserver(wp2);

        LogsController lc = new LogsController();
        game.getGameLogs().addObserver(lc);

        PlayerPanel player1 = new PlayerPanel(gc, g[0], true, wp1);
        PlayerPanel player2 = new PlayerPanel(gc, g[1], false, wp2);
        LogsPanel logs = new LogsPanel(lc);

//        game.startGame();

        StatsPanel stats1 = new StatsPanel();
        StatsPanel stats2 = new StatsPanel();


        MainView main = new MainView(player1, player2, logs, stats1, stats2);
        game.addObserver(main);
        game.setUpGameMode(GameMode.ISLAND);
        g[1].addWeaponToIslandTile(4, 4, WeaponType.BOMB);
        g[1].addWeaponToIslandTile(5, 4, WeaponType.SONAR);
        p.setStrat(new RandomPlacementStrategy());
        p.placeObject(pla1, g[0], null);
        p.placeObject(pla2, g[1], null);

        p1.addPlaceable(pla1);
        p2.addPlaceable(pla2);

        p1.getStats().setOpponentBoatStats(p2.getNbBoats(), p2.getNbBoatTiles());
        p2.getStats().setOpponentBoatStats(p1.getNbBoats(), p1.getNbBoatTiles());

        p1.getStats().addObserver(stats1);
        p2.getStats().addObserver(stats2);

        game.startGame();

        main.setVisible(true);


    }
}