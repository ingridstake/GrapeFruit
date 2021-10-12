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
     * A position could either be a NormalPosition, //TODO FLER POSITIONS
     * Throws an error if the positionType variable in a JsonPosition is an unacceptable String.
     * @param jsonPositionList contains JsonPositions
     * @return a list with IPositions
     */
    public static List<IPosition> makePositions(List<JsonPosition> jsonPositionList) {
        List<IPosition> positions = new ArrayList<>();

        for(JsonPosition pos : jsonPositionList) {
            switch(pos.positionType) {
                case "NormalPosition":
                    positions.add(new NormalPosition(pos.X, pos.Y));
                    break;
                default:
                    throw new IllegalArgumentException(pos.positionType
                            + " is not an acceptable IPosition!");
            }
        }
        return positions;
    }
}