package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.Listeners.DiceRolledListener;
import edu.chalmers.grapefruit.Utils.Listeners.OpenTileOperationsListener;
import edu.chalmers.grapefruit.Utils.Listeners.WinnerFoundListener;
import edu.chalmers.grapefruit.Utils.PlayerCardResource;
import edu.chalmers.grapefruit.Utils.ViewEntity;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Utils.Listeners.NewTurnListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static edu.chalmers.grapefruit.View.ViewUtils.getController;

/**
 * This is the main view that is created by the Controller.
 * The class holds the views of the game. Can switch between the views.
 *
 * @author Olivia Månström
 * @author Elvina Fahlgren
 */
public class MainView implements Observer, WinnerFoundListener {
    //TODO LÄGG TILL HASHMAP

    //TODO: lägga till javadoc?
    private @FXML AnchorPane mainViewPane;
    private GameBoardView gameBoardView = new GameBoardView();
    private StartView startView = new StartView();
    private EndView endView = new EndView();

    /**
     * Creates a static main view of the game.
     * @param stage is the stage of the application
     * @return the main view of the game
     * @throws IOException
     */
    static public MainView makeMainView (Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        System.setProperty("javafx.sg.warn", "true"); // Förhindrar exception vid scene loading, (https://stackoverflow.com/questions/44684605/javafx-applications-throw-nullpointerexceptions-but-run-anyway)
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        MainView view = fxmlLoader.getController();

        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();

        view.gameBoardView = GameBoardView.createGameBoardView();
        view.startView = StartView.createStartView();
        view.endView = EndView.createEndView();

        return view;
    }

    /**
     * Loads the start view to the main view's anchor pane.
     */
    public void loadStartView() {
        mainViewPane.getChildren().setAll(startView.getStartViewPane());
    }

    /**
     * Loads the game board view to the main view's anchor pane.
     */
    public void loadGameBoardView() {
        mainViewPane.getChildren().setAll(gameBoardView.getBackground());
    }

    /**
     * Populates the start view by calling the start view's populate method.
     * @param startGameHandler is the event handler that listens to an action to start a game
     * @param playerAmount is the amount of players
     * @throws IOException
     */
    public void populateStartView (EventHandler startGameHandler, int playerAmount) throws IOException {
        startView.populate(startGameHandler, playerAmount);
    }

    /**
     * Populates the game board view by calling the game board view's populate method.
     * @param viewEntities is the list of viewEntities components in game board
     * @param clickHandler is the event handler that listens to an action from a click on the game board
     * @param diceHandler is the event handler that listens to an action roll the dice
     * @param payToOpenBtnHandler
     * @param diceToOpenBtnHandler
     * @throws IOException
     */
    public void populateGameBoardView(List<ViewEntity> viewEntities, NodeClickHandler clickHandler,
                                      EventHandler diceHandler, EventHandler payToOpenBtnHandler,
                                      EventHandler diceToOpenBtnHandler) throws IOException {

        gameBoardView.populate(viewEntities, clickHandler, diceHandler, payToOpenBtnHandler, diceToOpenBtnHandler);
    }

    public void populateEndView(EventHandler exitGameHandler) {
        endView.populate(exitGameHandler);
    }

    public void addPlayerCards(List<PlayerCardResource> playerCardResources, List<Integer> ids) {
        gameBoardView.addPlayerCards(playerCardResources, ids);
    }

    /**
     * Returns the selected amount of players.
     * @return the selected amount of players.
     */
    public int getSelectedPlayerAmount(){
        return startView.getSelectedPlayerAmount();
    }

    public NewTurnListener getNewTurnListener() {
        return gameBoardView;
    }

    public OpenTileOperationsListener getOpenTileListener() {
        return gameBoardView;
    }

    public DiceRolledListener getDiceListener() {
        return gameBoardView.getDiceView();
    }

    @Override
    public void update() {
        gameBoardView.update();
    }

    @Override
    public void updateWinnerFound() {
        loadEndView();
    }

    /**
     * Loads the EndView to the main view.
     */
    private void loadEndView() {
        mainViewPane.getChildren().setAll(endView.getEndPane());
    }
}