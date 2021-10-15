package edu.chalmers.grapefruit.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class StartView {
    @FXML
    AnchorPane startViewPane;
    //@FXML Button startGameButton;

    FXMLLoader fxmlLoader;


    public StartView (){
        fxmlLoader = new FXMLLoader(StartView.class.getResource("start-view.fxml"));
        //fxmlLoader.setRoot(this);
        //fxmlLoader.setController(this);
/*
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

 */
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

}
