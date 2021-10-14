package edu.chalmers.grapefruit.Model.Tile;

/**
 * @author ingrid.stake
 */

public interface ITile {

    /**
     * Returns true if the tile has been turned.
     * @return true if the tile has been turned.
     */
    boolean isTurned();

    /**
     * Changes the tiles state to "turned".
     */
    void turnTile();

    /**
     * Returns the file name of the tile.
     * @return the file name of the tile
     */
    String getResourceString() throws Exception;

    /**
     * Returns the type of the tile
     * @return the type of the tile
     */
    TileType getTileType();
}