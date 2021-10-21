package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.View.MainView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class GameController {

    private GameModel model;
    private MainView view;

    /**
     * Creates and sets up the GameBoardView with background and positions
     * @param model the model of the controller
     * @param stage is the stage of the application
     * @throws IOException if the gameboard cannot be created.
     */
    public GameController(GameModel model, Stage stage) throws IOException {

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

        EventHandler payToOpenBtnHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                model.payToOpen();
            }
        };

        EventHandler diceToOpenBtnHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                model.diceToOpen();
            }
        };

        EventHandler startGameHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                //TODO skulle man kunna lägga in och kolla så att comboboxen är ikryssad här?
                try {
                    model.initialize(view.getSelectedPlayerAmount());
                    view.loadGameBoardPage();
                    view.populateGameBoardView(model.getViewEntities(), nodeClickEventHandler, diceHandler,
                            payToOpenBtnHandler, diceToOpenBtnHandler);
                    view.addPlayerCards(model.getPlayerCardResources(), model.getPlayerIds());
                    model.addNewTurnListener(view.getNewTurnListener());
                    model.addOpenTileListener(view.getOpenTileListener());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        };

        view = MainView.makeMainView(stage);
        view.loadStartPage();
        view.populateStartView(startGameHandler, 4);
        model.addObserver(view);
    }
}