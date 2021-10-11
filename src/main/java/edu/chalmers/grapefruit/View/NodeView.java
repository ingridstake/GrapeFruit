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
    static NodeClickHandler clickHandler;

    /**
     * Sets the click handler for all NodeViews
     * @param nodeClickHandler is the click handler for all node views.
     */
    static public void setClickHandler(NodeClickHandler nodeClickHandler){
        clickHandler = nodeClickHandler;
    }

    /**
     * Forwards the event to the event handler.
     */
    public void handleOnMouseClicked() {
        clickHandler.handle(x, y);
    }

    public void initialize(int x, int y) {
        this.x = x;
        this.y = y;
    }
}