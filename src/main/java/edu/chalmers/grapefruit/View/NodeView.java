package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Controller.NodeClickHandler;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class NodeView {

    private int x;
    private int y;
    @FXML public Circle position;
    NodeClickHandler clickHandler;

    public void handleOnMouseClicked() {
        clickHandler.handle(x, y);
    }
    public void initialize(NodeClickHandler clickHandler, int x, int y) {
        this.clickHandler = clickHandler;
        this.x = x;
        this.y = y;
    }
}