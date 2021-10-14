package edu.chalmers.grapefruit.Model.Position;

import edu.chalmers.grapefruit.Model.Tile.ITile;

/**
 * @author tovenilsson
 */

public class CityPosition implements IPosition{

    private final IPosition POSITION;
    private ITile TILE;

    protected CityPosition(int x, int y){
        POSITION = new NormalPosition(x, y);
    }

    public void setTile(ITile tile) {
        TILE = tile;
    }

    @Override
    public int getX() {
        return POSITION.getX();
    }

    @Override
    public int getY() {
        return POSITION.getY();
    }

    @Override
    public String getResourceString() {
        if (POSITION.isHighlighted()) {
            return "tile-view-highlighted.fxml";
        }
        try {
            return TILE.getResourceString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void highlight() {
        POSITION.highlight();
    }

    @Override
    public void deHighlight() {
        POSITION.deHighlight();
    }

    @Override
    public boolean isHighlighted() {
        return false;
    }
}