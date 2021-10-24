package edu.chalmers.grapefruit.View;

import javafx.scene.Node;

public class ViewUtils {

    /**
     * Finds and returns the fx:controller of a Node.
     * From https://stackoverflow.com/questions/40754454/get-controller-instance-from-node
     * @param node is the node of interest.
     * @return the fx:controller of the node.
     */
    public static Object getController(Node node) {
        Object controller = null;
        do {
            controller = node.getUserData();
            node = node.getParent();
        } while (controller == null && node != null);
        return controller;
    }
}
