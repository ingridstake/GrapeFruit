package edu.chalmers.grapefruit;

import edu.chalmers.grapefruit.Controller.GameBoardController;
import edu.chalmers.grapefruit.Controller.HelloController;
import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.View.GameBoardView;
import edu.chalmers.grapefruit.View.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("background.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);

         */
        GameModel model = new GameModel();
        GameBoardController controller = new GameBoardController(model, stage);

        stage.show();
    }
/*
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        HelloController controller = new HelloController(model, view);

        launch();
    }
 */
}