package edu.chalmers.grapefruit.View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

/**
 * The start view.
 * @author Olivia Månström
 * @author Elvina Fahlgren
 */
public class StartView {

    @FXML private AnchorPane startViewPane;
    @FXML private Button startGameButton;
    @FXML private ChoiceBox playerAmountChoiceBox;

    public static StartView createStartView() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardView.class.getResource("start-view.fxml"));
        AnchorPane view = fxmlLoader.load();

        StartView startView = getStartViewController(view);
        startView.startViewPane = view;

        return startView;
    }

    private static StartView getStartViewController(Node node) throws Exception {
        Object fxController = ViewUtils.getController(node);
        StartView startView = fxController instanceof StartView ? (StartView) fxController : null;

        if(startView == null) {
            throw new Exception("The argument node's fx:controller is not an instance of StartView");
        }

        return startView;
    }

    /**
     * Populates the start view.
     * Sets action to the start game button.
     * Adds items, as numbers, to the choice box.
     * @param startGameHandler is the event handler that listens to an action to start a game.
     * @param playerAmount is the amount of players
     */
    public void populate (EventHandler startGameHandler, int playerAmount) {
        startGameButton.setOnAction(startGameHandler);

        for(int i = 1; i <= playerAmount; i++) {
            playerAmountChoiceBox.getItems().add(i);
        }
        playerAmountChoiceBox.getSelectionModel().select(1);
    }

    /**
     * Returns a int from the choice box.
     * @return a int from the choice box.
     */
    public int getSelectedPlayerAmount(){
        return Integer.parseInt(playerAmountChoiceBox.getValue().toString());
    }

    public AnchorPane getStartViewPane() {
        return startViewPane;
    }
}