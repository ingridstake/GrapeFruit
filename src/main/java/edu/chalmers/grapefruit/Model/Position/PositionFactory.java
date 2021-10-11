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
     * Makes IPositions based on the each JSONObject in the param JSONArray.
     * @param jsonArray contains JSONObjects named "position". Each "position" have
     *                  - a String "positionType" that holds the IPosition type (NormalPosition, CityPosition,
     *                  StartPosition or BoatPosition)
     *                  - an int "X" that holds the x value of the IPosition
     *                  - an int "Y" that holds the y value of the IPosition
     * @return a list with IPositions
     */
    public static List<IPosition> makePositions(List<JsonPosition> jsonArray) {
        List<IPosition> positions = new ArrayList<>();

        for(JsonPosition pos : jsonArray) {
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