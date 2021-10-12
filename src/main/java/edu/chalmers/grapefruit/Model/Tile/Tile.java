package edu.chalmers.grapefruit.Model.Tile;

import edu.chalmers.grapefruit.Utils.IPositionable;

public class Tile implements ITile {

    private final TileType TILE_TYPE;
    private boolean isTurned;

    protected Tile (TileType tileType){
        TILE_TYPE = tileType;
        isTurned = false;
    }

    @Override
    public void turnTile(){
        isTurned = true;
    }

    @Override
    public boolean isTurned() {
        return isTurned;
    }

    @Override
    public TileType getTileType() {
        return null;
    }

    @Override
    public String getResourceString() {
        if (isTurned()){
            try {
                return TileType.evaluateResourceString(TILE_TYPE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return "tile-unturned.fxml";
    }


}