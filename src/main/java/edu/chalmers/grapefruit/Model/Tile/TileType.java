package edu.chalmers.grapefruit.Model.Tile;

public enum TileType {
    COW, HORSE, PIG, POOP, COWBELL, BLANK;

    public static String evaluateResourceString(TileType tileType) throws Exception {

        switch (tileType){
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
            default :
                throw new Exception("Could not find resourceString for " + tileType.toString());
        }
    }
}
