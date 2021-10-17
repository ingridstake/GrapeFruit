package edu.chalmers.grapefruit.View;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartView {
    @FXML AnchorPane startViewPane;
    @FXML Button startGameButton;
    @FXML ChoiceBox playerAmountChoiceBox;
    FXMLLoader fxmlLoader;

    public StartView (){
        fxmlLoader = new FXMLLoader(StartView.class.getResource("start-view.fxml"));
/*
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

 */
    }

    public void populate (EventHandler startGameHandler, int playerAmount) throws IOException {
        startGameButton.setOnAction(startGameHandler);

        for(int i = 1; i <= playerAmount; i++) {
            playerAmountChoiceBox.getItems().add(i);
        }
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

}
