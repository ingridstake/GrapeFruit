package edu.chalmers.grapefruit.View;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The start view.
 * @author Olivia Månström
 * @author Elvina Fahlgren
 */
public class StartView {
    @FXML AnchorPane startViewPane;
    @FXML Button startGameButton;
    @FXML ChoiceBox playerAmountChoiceBox;
    FXMLLoader fxmlLoader;

    /**
     * Creates a FXMLLoader that represents the start view.
     */
    public StartView (){
        fxmlLoader = new FXMLLoader(StartView.class.getResource("start-view.fxml"));
    }

    /**
     * Populates the start view.
     * Sets action to the start game button.
     * Adds items, as numbers, to the choice box.
     * @param startGameHandler is the event handler that listens to an action to start a game
     * @param playerAmount is the amount of players
     */
    public void populate (EventHandler startGameHandler, int playerAmount) {
        startGameButton.setOnAction(startGameHandler);

        for(int i = 1; i <= playerAmount; i++) {
            playerAmountChoiceBox.getItems().add(i);
        }
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }
}