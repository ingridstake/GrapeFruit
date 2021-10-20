package edu.chalmers.grapefruit.Utils;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 */

public interface NodeClickHandler {

    /**
     * Handles a Node click from view depending on the nodes position.
     * @param x the x-value of the Node
     * @param y the y-value of the Node
     */
    void handle (int x, int y);
}