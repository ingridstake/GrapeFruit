package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Utils.ViewEntityResource;

/**
 * @author ingrid.stake
 * @author tovenilsson
 */

public class ViewEntity {

    ViewEntityResource resource;

    public ViewEntity (ViewEntityResource resource){
        this.resource = resource;
    }

    public int getX() {
        return resource.getPoint().x;
    }

    public int getY() {
        return resource.getPoint().y;
    }

    /**
     * Returns the resource string needed for display
     * @return the resourceString of the resource.
     */
    public String getResourceString() {
        try {
            return resource.getResourceString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}