package main.java.edu.chalmers.tda367.project.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.java.edu.chalmers.tda367.project.Model.GameBoard.Node;
import main.java.edu.chalmers.tda367.project.Model.GameModel;

import java.util.List;

public class GameBoardController {
    @FXML AnchorPane background;
    @FXML List<Circle> nodes;

    public GameBoardController(GameModel model) {
        Node startNode = model.gameBoard.getMap().getStartNode();

    }




}
