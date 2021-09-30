package edu.chalmers.grapefruit.View;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class GameBoardView {
    AnchorPane anchorPane;

    public GameBoardView ( int nNodes) {
        anchorPane = new AnchorPane();
        List<Circle> circleList = new ArrayList<>();
        for (int i = 0; i<nNodes; i++){
            circleList.add(new Circle());
        }

        anchorPane.getChildren().addAll(circleList);

    }
}
