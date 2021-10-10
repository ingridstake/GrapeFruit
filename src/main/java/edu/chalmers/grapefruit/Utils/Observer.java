package edu.chalmers.grapefruit.Utils;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 */

public interface Observer {

    /**
     * Is called when The Observable has been updated.
     */
    void update();
}