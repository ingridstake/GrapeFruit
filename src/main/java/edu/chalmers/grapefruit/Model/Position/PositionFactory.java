package edu.chalmers.grapefruit.Model.Position;

import java.util.*;

import edu.chalmers.grapefruit.Model.Json.JsonPosition;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class PositionFactory {
    /**
     * Makes IPositions based on JsonPositions.
     * A position could either be a NormalPosition, a CityPosition or a StartPosition
     * Throws an error if the positionType variable in a JsonPosition is an unacceptable String.
     * @param jsonPositionList contains JsonPositions
     * @return a list with IPositions
     */
    public static List<IPosition> makePositions(List<JsonPosition> jsonPositionList) {
        List<IPosition> positions = new ArrayList<>();
        List<TilePosition> cityPositions = new ArrayList<>();

        for(JsonPosition pos : jsonPositionList) {
            switch(pos.positionType) {
                case "NormalPosition":
                    positions.add(new Position(pos.X, pos.Y ));
                    break;
                case "CityPosition":
                    TilePosition cityPosition = new TilePosition(pos.X, pos.Y);
                    positions.add(cityPosition);
                    cityPositions.add(cityPosition);
                    break;
                case "StartPosition":
                    positions.add(new Position(pos.X, pos.Y, LogicType.START));
                    break;
                default:
                    throw new IllegalArgumentException(pos.positionType
                            + " is not an acceptable IPosition!");
            }
        }

        setTiles(cityPositions);

        return positions;
    }

    /**
     * Gives all the tilePositions a random tile.
     * @param tilePositions is the list of tile positions that should be assigned a Tile.
     */
    private static void setTiles(List<TilePosition> tilePositions) {

        List<LogicType> tiles = getTileTypes(tilePositions.size());

        for (int i = 0; i < tilePositions.size(); i++) {
            tilePositions.get(1).setLogicType(tiles.get(i));
        }
    }

    /**
     * Returns a shuffled list of tile logicTypes with the right split between them.
     * @param nTiles is the number of tiles that needs to be returned.
     * @return a list of tiles.
     */
    private static List<LogicType> getTileTypes(int nTiles) {
        List<LogicType> tileTypes = new ArrayList<>();

        tileTypes.add(LogicType.COW);

        for (int i = 0; i < nTiles * 2 / 15; i++) {
            tileTypes.add(LogicType.HORSE);
        }

        for (int i = 0; i < nTiles / 5; i++) {
            tileTypes.add(LogicType.PIG);
        }

        for (int i = 0; i < 4 * nTiles / 15; i++) {
            tileTypes.add(LogicType.COWBELL);
        }

        for (int i = 0; i < nTiles / 5; i++) {
            tileTypes.add(LogicType.POOP);
        }

        while (tileTypes.size() < nTiles) {
            tileTypes.add(LogicType.BLANK);
        }

        Collections.shuffle(tileTypes);

        return tileTypes;
    }
}