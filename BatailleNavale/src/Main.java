import controllers.*;
import models.game.Game;
import models.game.placement.ConfigData;
import models.game.placement.Placement;
import models.game.placement.RandomPlacementStrategy;
import models.grid.Grid;
import models.player.AIPlayer;
import models.player.HumanPlayer;
import models.player.Player;
import views.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initialisation de la Bataille Navale...");

        Grid player1Grid = new Grid(10);
        Grid player2Grid = new Grid(10);

        Player humanPlayer = new HumanPlayer("Joueur Humain", 0, player1Grid);
        Player aiPlayer = new AIPlayer("IA", 1, player2Grid);

        Game game = new Game(humanPlayer, aiPlayer);

        Placement placementManager = new Placement(new RandomPlacementStrategy());

        WeaponController humanWeaponController = new WeaponController(humanPlayer);
        WeaponController aiWeaponController = new WeaponController(aiPlayer);

        WeaponPanel humanWeaponPanel = new WeaponPanel(humanWeaponController);
        WeaponPanel aiWeaponPanel = new WeaponPanel(aiWeaponController);

        humanPlayer.addWeaponObserver(humanWeaponPanel);
        aiPlayer.addWeaponObserver(aiWeaponPanel);

        ConfigData configData = new ConfigData(humanPlayer, aiPlayer);
        ConfigController configController = new ConfigController(configData);
        GameController gameController = new GameController(game, configData);

        PlayerPanel humanPlayerPanel = new PlayerPanel(player1Grid, true, humanWeaponPanel);
        PlayerPanel aiPlayerPanel = new PlayerPanel(gameController, player2Grid, false, aiWeaponPanel);

        ConfigPanel configPanel = new ConfigPanel(configController, gameController);

        LogsController lc = new LogsController();
        game.getGameLogs().addObserver(lc);


        PlacementController placementController = new PlacementController(
                placementManager,
                humanPlayer,
                gameController
        );

        PlacementController placementControllerAi = new PlacementController(
                placementManager,
                aiPlayer,
                gameController
        );

        PlacementView placementView = new PlacementView(
                placementController,
                placementControllerAi,
                player1Grid,
                gameController
        );

        StatsPanel stats1 = new StatsPanel();
        StatsPanel stats2 = new StatsPanel();
        humanPlayer.getStats().addObserver(stats1);
        aiPlayer.getStats().addObserver(stats2);

        LogsPanel logs = new LogsPanel(lc);


        MainView mainView = new MainView(
                configPanel,
                placementView,
                humanPlayerPanel,
                aiPlayerPanel,
                logs,
                stats1,
                stats2,
                gameController
        );

        game.addObserver(mainView);

        mainView.setVisible(true);

        System.out.println("Jeu initialisé avec succès !");
    }
}