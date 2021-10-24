package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.Utils.PlayerCardResourceFactory;
import edu.chalmers.grapefruit.Utils.ViewEntityFactory;
import edu.chalmers.grapefruit.View.MainView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class GameController {

    private GameModel model;
    private MainView view;

    /**
     * Creates and sets up the GameBoardView with background and positions.
     * @param model the model of the controller
     * @param stage is the stage of the application
     * @throws IOException if the game board cannot be created.
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

        EventHandler exitGameHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.exit(0);
            }
        };

        EventHandler startGameHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    model.initialize(view.getSelectedPlayerAmount());
                    view.loadGameBoardView();
                    view.populateGameBoardView(ViewEntityFactory.createViewEntities(model), nodeClickEventHandler, diceHandler, payToOpenBtnHandler, diceToOpenBtnHandler);
                    view.addPlayerCards(PlayerCardResourceFactory.createPlayerCardResources(model), model.getPlayerIds());
                    addListeners();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        try {
            view = MainView.makeMainView(stage);
            view.loadStartView();
            view.populateStartView(startGameHandler, 4);
            view.populateEndView(exitGameHandler);
            model.addObserver(view.getObserver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        model.addNewTurnListener(view.getNewTurnListener());
        model.addOpenTileListener(view.getOpenTileListener());
        model.addDiceRolledListener(view.getDiceListener());
        model.addWinnerFoundListener(view);
    }
}