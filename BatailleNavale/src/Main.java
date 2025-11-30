import controllers.ConfigController;
import models.game.placement.ConfigData;
import models.grid.Grid;
import views.ConfigScreen;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Grid[] grids = new Grid[2];
        grids[0] = new Grid(5);
        grids[1] = new Grid(5);

        ConfigData conf = new ConfigData(grids);

        ConfigController configController = new ConfigController(conf);


        ConfigScreen configScreen = new ConfigScreen(configController);
        configScreen.setVisible(true);


    }
}