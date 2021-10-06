package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.View.GameBoardView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardController {

    private GameModel model;
    private GameBoardView view;

    private NodeClickHandler nodeClickEventHandler = new NodeClickHandler() {
        @Override
        public void handle(int x, int y) {
            System.out.println("Nu funkar det snart");
        }
    };

    /**
     * Creates and sets up the GameBoardView with background and positions
     * @param model the model of the controller
     * @param stage is the stage of the application
     * @throws IOException if the resource-.fxml cannot be loaded
     */
    public GameBoardController(GameModel model, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("background.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        view = fxmlLoader.getController();
        this.model = model;

        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();

        view.populate(model.getPositionables(), this);
    }

    public NodeClickHandler getNodeClickEventHandler(){
        return nodeClickEventHandler;
    }
}