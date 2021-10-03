package edu.chalmers.grapefruit.Controller;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.View.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public HelloController(GameModel gameModel, GameView gameView) {

    }
}