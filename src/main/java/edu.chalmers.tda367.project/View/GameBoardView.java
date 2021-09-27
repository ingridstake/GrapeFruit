package main.java.edu.chalmers.tda367.project.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import main.java.edu.chalmers.tda367.project.Controller.GameBoardController;
import main.java.edu.chalmers.tda367.project.Controller.NodeView;
import main.java.edu.chalmers.tda367.project.Model.GameBoard.Node;
import main.java.edu.chalmers.tda367.project.Model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class GameBoardView extends AnchorPane {

    @FXML private AnchorPane background;
    @FXML private List<javafx.scene.Node> positions;

    public GameBoardView(GameModel model, GameBoardController controller) throws Exception {
        List<Node> modelNodes = model.gameBoard.getMap().getAllNodes();


        Parent root = FXMLLoader.load(getClass().getResource("background.fxml"));

/*
        positions = new ArrayList<>();

        for (Node node : modelNodes) {
            Circle circle = new Circle();
            circle.setCenterX(node.getPosition().getX());
            circle.setCenterY(node.getPosition().getY());
            circle.setRadius(40);

            positions.add(circle);
        }
        background = new AnchorPane(positions.toArray())

 */


    }
}
