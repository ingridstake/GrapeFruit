package edu.chalmers.grapefruit.Model.Tile;

public interface ITile {

    boolean isTurned();
    void turnTile();
    String getResourceString();
    TileType getTileType();

}
