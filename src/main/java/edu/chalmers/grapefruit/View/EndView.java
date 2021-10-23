package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.Listeners.WinnerFoundListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.event.EventHandler;


public class EndView {

    @FXML private AnchorPane endPane;
    @FXML Button exitButton;
    @FXML private Button reRunButton;
    private FXMLLoader fxmlLoader;


    public EndView(){
        fxmlLoader = new FXMLLoader(EndView.class.getResource("end-view.fxml"));
    }

    public void populate(EventHandler exitGameHandler, EventHandler reRunGameHandler){
        exitButton.setOnAction(exitGameHandler);
        reRunButton.setOnAction(reRunGameHandler);
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    public AnchorPane getEndPane() {
        return endPane;
    }

}
