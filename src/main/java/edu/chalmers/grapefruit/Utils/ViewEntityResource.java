package edu.chalmers.grapefruit.Utils;

import java.awt.*;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 */

public interface ViewEntityResource {

    Point getPoint();

    /**
     * Name of the view-resource
     * @return name of the view-resource
     */
    String getResourceString();
}