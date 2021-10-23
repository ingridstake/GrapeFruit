package edu.chalmers.grapefruit.Utils;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
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
        return resource.getResourceString();
    }
}