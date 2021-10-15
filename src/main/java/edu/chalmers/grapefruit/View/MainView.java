package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Utils.IPositionable;
import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainView {
    @FXML
    AnchorPane mainViewPane;

    private GameBoardViewTest gameBoardView = new GameBoardViewTest();
    private StartView startView = new StartView();

    static public MainView makeMainView(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        System.setProperty("javafx.sg.warn", "true"); // Förhindrar exception vid scene loading, (https://stackoverflow.com/questions/44684605/javafx-applications-throw-nullpointerexceptions-but-run-anyway)
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

        MainView view = fxmlLoader.getController();

        stage.setTitle("Den försvunna kossan!");
        stage.setScene(scene);
        stage.show();

        return view;
    }

    private void createStartView() throws IOException {
        mainViewPane.getChildren().removeAll();
        mainViewPane.getChildren().add(startView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        startView = (StartView) getStartViewController(child);
    }


    public void loadStartPage() throws IOException {
        createStartView();
        mainViewPane.getChildren().setAll(startView.startViewPane);
    }


    public static Object getStartViewController(Node startView) {
        Object controller = null;
        do {
            controller = startView.getUserData();
            startView = startView.getParent();
        } while (controller == null && startView != null);
        return controller;
    }

    private void createGameBoardView() throws IOException {
        mainViewPane.getChildren().removeAll();
        mainViewPane.getChildren().add(gameBoardView.getFXMLLoader().load());
        javafx.scene.Node child = mainViewPane.getChildren().get(0);
        gameBoardView = (GameBoardViewTest) getGameBoardController(child);
    }


    public void loadGameBoardPage() throws IOException {
        createGameBoardView();
        mainViewPane.getChildren().setAll(gameBoardView.background);
    }


    public static Object getGameBoardController(Node gameBoardView) {
        Object controller = null;
        do {
            controller = gameBoardView.getUserData();
            gameBoardView = gameBoardView.getParent();
        } while (controller == null && gameBoardView != null);
        return controller;
    }

    public void populate (List<IPositionable> positionableList, NodeClickHandler clickHandler, EventHandler diceHandler) throws IOException {
        gameBoardView.populate(positionableList, clickHandler, diceHandler);
    }
}







