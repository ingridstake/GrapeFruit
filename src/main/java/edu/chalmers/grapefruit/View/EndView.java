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
    private FXMLLoader fxmlLoader;


    public EndView(){
        fxmlLoader = new FXMLLoader(EndView.class.getResource("end-view.fxml"));
    }

    public void populate(EventHandler exitGameHandler){
        exitButton.setOnAction(exitGameHandler);
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }

    public AnchorPane getEndPane() {
        return endPane;
    }

}
