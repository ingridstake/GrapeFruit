package edu.chalmers.grapefruit.View;

import edu.chalmers.grapefruit.Utils.NodeClickHandler;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 */

public class NodeView {

    private int x;
    private int y;
    @FXML public Circle position;
    NodeClickHandler clickHandler;

    /**
     * Forwards the event to the event handler.
     */
    public void handleOnMouseClicked() {
        clickHandler.handle(x, y);
    }

    public void initialize(NodeClickHandler clickHandler, int x, int y) {
        this.clickHandler = clickHandler;
        this.x = x;
        this.y = y;
    }
}