package edu.chalmers.grapefruit.Model.Position;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 */

public class TilePosition extends Position{

    private boolean isTurned;

    public TilePosition(int x, int y) {
        super(x, y);
        isTurned = false;
    }

    /**
     * Returns the logic type of the tile position based on whether the tile has been turned or not.
     * @return UNTURNED_TILE if the tile has not been turned yet.
     */
    @Override
    public LogicType getLogicType() {
        if (isTurned) {
            return super.getLogicType();
        }
        return LogicType.UNTURNED_TILE;
    }

    @Override
    public void setLogicType(LogicType logicType){
        super.setLogicType(logicType);
    }

    public void turnTile(){
        isTurned = true;
    }
}