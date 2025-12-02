import controllers.ConfigController;
import models.game.placement.ConfigData;
import models.grid.Grid;
import views.ConfigPanel;
import views.MainView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Grid[] grids = new Grid[2];
        grids[0] = new Grid(5);
        grids[1] = new Grid(5);

        ConfigData conf = new ConfigData(grids);

        ConfigController configController = new ConfigController(conf);


        ConfigPanel configScreen = new ConfigPanel(configController);
        MainView main = new MainView();
        main.setConfig(configScreen);
        main.setVisible(true);
        main.displayConfig();


    }
}