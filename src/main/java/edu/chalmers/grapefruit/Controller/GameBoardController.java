package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.View.GameBoardView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardController {

    private GameModel model;
    private GameBoardView view;

    private NodeClickHandler nodeClickEventHandler = new NodeClickHandler() {
        @Override
        public void handle(int x, int y) {
            model.makePlayerMove(x,y);
        }
    };

    /**
     * Creates and sets up the GameBoardView with background and positions
     * @param model the model of the controller
     * @param stage is the stage of the application
     * @throws IOException if the resource-.fxml cannot be loaded
     */
    public GameBoardController(GameModel model, Stage stage) throws IOException {

        this.model = model;

        view = GameBoardView.makeGameBoardView(stage);

        view.populate(model.getPositionables(), getNodeClickEventHandler());
        model.addObserver(view);
    }

    public NodeClickHandler getNodeClickEventHandler(){
        return nodeClickEventHandler;
    }
}