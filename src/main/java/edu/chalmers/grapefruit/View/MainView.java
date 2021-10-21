package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Model.GameBoard.CurrentPlayer;
import edu.chalmers.grapefruit.Model.PlayerCardResource;
import edu.chalmers.grapefruit.Model.ViewEntity;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import edu.chalmers.grapefruit.Utils.Observer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This is the main view that is created by the Controller.
 * The class holds the views of the game. Can switch between the views.
 *
 * @author olimanstorm
 * @author elvinafahlgren
 */
public class MainView implements Observer {
    //TODO LÄGG TILL HASHMAP
    private @FXML AnchorPane mainViewPane;
    private GameBoardView gameBoardView = new GameBoardView();
    private StartView startView = new StartView();

    /**
     * Creates a static main view of the game.
     * @param stage is the stage of the application
     * @return the main view of the game
     * @throws IOException
     */
    static public MainView makeMainView (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        System.setProperty("javafx.sg.warn", "true"); // Förhindrar exception vid scene loading, (https://stackoverflow.com/questions/44684605/javafx-applications-throw-nullpointerexceptions-but-run-anyway)
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        MainView view = fxmlLoader.getController();

        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();

        return view;
    }

    /**
     * Sets the start view to the main view's anchor pane.
     * @throws IOException
     */
    public void loadStartPage () throws IOException {
        createStartView();
        mainViewPane.getChildren().setAll(startView.startViewPane);
    }

    /**
     * Sets the game board view to the main view's anchor pane.
     * @throws IOException
     */
    public void loadGameBoardPage () throws IOException {
        createGameBoardView();
        mainViewPane.getChildren().setAll(gameBoardView.background);
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
    public void populateGameBoardView(List<ViewEntity> viewEntities, NodeClickHandler clickHandler, EventHandler diceHandler, EventHandler payToOpenBtnHandler, EventHandler diceToOpenBtnHandler, boolean currentPlayerHasMoneyToOpenTile) throws IOException {
        gameBoardView.populate(viewEntities, clickHandler, diceHandler, payToOpenBtnHandler, diceToOpenBtnHandler, currentPlayerHasMoneyToOpenTile);
    }

    /*
    public void addPlayerCards(List<PlayerCardResource> playerCardResources, CurrentPlayer currentPlayer) throws IOException {
        gameBoardView.addPlayerCards(playerCardResources, currentPlayer);
    }
    */
    public void addPlayerCards(List<PlayerCardResource> playerCardResources, int currentPlayerId) throws IOException {
        gameBoardView.addPlayerCards(playerCardResources, currentPlayerId);
    }

    private void createStartView () throws IOException {
        mainViewPane.getChildren().removeAll(mainViewPane.getChildren());
        mainViewPane.getChildren().add(startView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        startView = (StartView) getController(child);
    }

    private void createGameBoardView () throws IOException {
        mainViewPane.getChildren().removeAll(mainViewPane.getChildren());
        mainViewPane.getChildren().add(gameBoardView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        gameBoardView = (GameBoardView) getController(child);
    }

    private static Object getController(Node node) {
        Object controller = null;
        do {
            controller = node.getUserData();
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }

    /**
     * Returns the selected amount of players.
     * @return the selected amount of players.
     */
    public int getSelectedPlayerAmount(){
        return startView.getSelectedPlayerAmount();
    }

    @Override
    public void update() {
        gameBoardView.update();
    }
}