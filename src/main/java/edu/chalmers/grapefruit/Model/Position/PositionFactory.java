package edu.chalmers.grapefruit.Model.Position;

import java.io.*;
import java.util.*;

import org.json.*;
import java.io.FileReader;

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
    public static List<IPosition> makePositions(JSONArray jsonArray) {
        List<IPosition> positions = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject current = jsonArray.getJSONObject(i);
            switch (current.getJSONObject("position").getString("positionType")) {
                case "NormalPosition":
                    positions.add(new NormalPosition(current.getJSONObject("position").getInt("X"), current.getJSONObject("position").getInt("Y")));
                    break;
                default:
                    throw new IllegalArgumentException(current.getJSONObject("position").getString("positionType")
                            + " is not an acceptable IPosition!");
            }
        }
        return positions;
    }

}
