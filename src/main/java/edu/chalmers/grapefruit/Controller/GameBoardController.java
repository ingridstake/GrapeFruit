package edu.chalmers.grapefruit.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameBoardController {
    @FXML private AnchorPane background;
    int nNodes;

    @FXML
    private void initialize() throws IOException {
        for (int i = 0; i < nNodes; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("node-view.fxml"));
            background.getChildren().add(fxmlLoader.load());
        }
        int i = 1;
        for (var child : background.getChildren()) {
            child.relocate(i*50, i*50);
            i++;
        }

    }

    public GameBoardController (int nNodes){
        background = new AnchorPane();
        this.nNodes = nNodes;
    }

    public GameBoardController (){
        background = new AnchorPane();
        this.nNodes = 10;
    }
}
