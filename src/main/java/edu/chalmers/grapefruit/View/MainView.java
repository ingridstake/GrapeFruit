package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.IPositionable;
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

public class MainView implements Observer {
    @FXML AnchorPane mainViewPane;


    private GameBoardViewTest gameBoardView = new GameBoardViewTest();
    private StartView startView = new StartView();

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

    public void loadStartPage () throws IOException {
        createStartView();
        mainViewPane.getChildren().setAll(startView.startViewPane);
    }

    public void loadGameBoardPage () throws IOException {
        createGameBoardView();
        mainViewPane.getChildren().setAll(gameBoardView.background);
    }

    public void populateStartPage (EventHandler startGameHandler, int playerAmount) throws IOException {
        startView.populate(startGameHandler, playerAmount);
    }

    public void populateGameBoardPage (List<IPositionable> positionableList, NodeClickHandler clickHandler, EventHandler diceHandler) throws IOException {
        gameBoardView.populate(positionableList, clickHandler, diceHandler);
    }

    private void createStartView () throws IOException {
        mainViewPane.getChildren().removeAll();
        mainViewPane.getChildren().add(startView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        startView = (StartView) getController(child);
    }

    private void createGameBoardView () throws IOException {
        mainViewPane.getChildren().removeAll(mainViewPane.getChildren());
        mainViewPane.getChildren().add(gameBoardView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        System.out.println(mainViewPane.getChildren());
        gameBoardView = (GameBoardViewTest) getController(child);
    }

    private static Object getController(Node node) {
        Object controller = null;
        do {
            controller = node.getUserData();
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }

    @Override
    public void update() {
        gameBoardView.update();
    }
}







