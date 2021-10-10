package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.View.GameBoardView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;

public class GameBoardController {

    private GameModel model;
    private GameBoardView view;

    /**
     * Creates and sets up the GameBoardView with background and positions
     * @param model the model of the controller
     * @param stage is the stage of the application
     * @throws IOException if the gameboard cannot be created.
     */
    public GameBoardController(GameModel model, Stage stage) throws IOException {

        this.model = model;

        NodeClickHandler nodeClickEventHandler = new NodeClickHandler() {
            @Override
            public void handle(int x, int y) {
                model.makePlayerMove(x,y);
            }
        };

        EventHandler diceHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                model.makeDiceRoll();
            }
        };

        view = GameBoardView.makeGameBoardView(stage);
        view.populate(model.getPositionables(), nodeClickEventHandler, diceHandler);
        model.addObserver(view);
    }
}