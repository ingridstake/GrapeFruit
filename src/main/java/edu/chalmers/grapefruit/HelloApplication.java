package edu.chalmers.grapefruit;

import edu.chalmers.grapefruit.Controller.GameBoardController;
import edu.chalmers.grapefruit.Model.GameModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        GameModel model = new GameModel();
        GameBoardController controller = new GameBoardController(model, stage);

        stage.show();
    }
}