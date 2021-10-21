package edu.chalmers.grapefruit;

import edu.chalmers.grapefruit.Controller.GameController;
import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Utils.PlayerCardResourceFactory;
import edu.chalmers.grapefruit.Utils.ViewEntityFactory;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        GameModel model = new GameModel();
        GameController controller = new GameController(model, stage);

        stage.show();
    }
}