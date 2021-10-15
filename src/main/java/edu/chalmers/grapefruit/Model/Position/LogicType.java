package edu.chalmers.grapefruit.Model.Position;

/**
 * @author ingrid.stake
 * @author tovenilsson
 */

public enum LogicType {

    NONE, UNTURNED_TILE, START, COW, HORSE, PIG, POOP, COWBELL, BLANK;

    /**
     * Returns the suitable resource string for the current state of the logic type holder.
     * @param logicType is the logic type that is to be evaluated
     * @param isHighLighted determines which version of the resource is to be returned.
     * @return the evaluated resourceString.
     * @throws Exception Throws exception if the logicType has no resource.
     */
    public static String evaluatePositionResource (LogicType logicType, boolean isHighLighted) throws Exception {
        if (isHighLighted){
            return getHighLightedResource(logicType);
        }
        return getNormalResource(logicType);
    }

    private static String getHighLightedResource(LogicType logicType) throws Exception {
        switch (logicType){
            case NONE:
                return "node-view-highLighted.fxml";
            case COW :
                return "cow-tile.fxml";
            case PIG :
                return "pig-tile.fxml";
            case POOP :
                return "poop-tile.fxml";
            case BLANK :
                return "blank-tile.fxml";
            case HORSE :
                return "horse-tile.fxml";
            case COWBELL :
                return "cowbell-tile.fxml";
            case START:
                return "startPosition-highlighted-view.fxml";
            case UNTURNED_TILE:
                return "tile-view-highlighted.fxml";
            default :
                throw new Exception("Could not find resourceString for highlighted " + logicType.toString());
        }
    }

    private static String getNormalResource(LogicType logicType) throws Exception {

        switch (logicType){
            case NONE:
                return "node-view.fxml";
            case COW :
                return "cow-tile.fxml";
            case PIG :
                return "pig-tile.fxml";
            case POOP :
                return "poop-tile.fxml";
            case BLANK :
                return "blank-tile.fxml";
            case HORSE :
                return "horse-tile.fxml";
            case COWBELL :
                return "cowbell-tile.fxml";
            case START:
                return "startPosition-view.fxml";
            case UNTURNED_TILE:
                return "tile-unturned.fxml";
            default :
                throw new Exception("Could not find resourceString for " + logicType.toString());
        }
    }
}
