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

    public static IPosition startPosition;

    /**
     * Creates a Hashmap of positions with different coordinates.
     * @return a Hashmap of positions
     */
    public static HashMap<IPosition, List<IPosition>> makePositions() {
        HashMap<IPosition, List<IPosition>> positionListHashMap = new HashMap<>();
        /*

        positions.add(new NormalPosition(30, 30));
        positions.add(new NormalPosition(30, 50));
        positions.add(new NormalPosition(30, 70));
        positions.add(new NormalPosition(50, 30));
        positions.add(new NormalPosition(50, 70));
        positions.add(new NormalPosition(70, 30));
        positions.add(new NormalPosition(70, 50));
        positions.add(new NormalPosition(70, 70));

        positionListHashMap.put(positions.get(0), Arrays.asList(positions.get(7), positions.get(1)) );
        positionListHashMap.put(positions.get(1), Arrays.asList(positions.get(0), positions.get(2)) );
        positionListHashMap.put(positions.get(2), Arrays.asList(positions.get(1), positions.get(3)) );
        positionListHashMap.put(positions.get(3), Arrays.asList(positions.get(2), positions.get(4)) );
        positionListHashMap.put(positions.get(4), Arrays.asList(positions.get(3), positions.get(5)) );
        positionListHashMap.put(positions.get(5), Arrays.asList(positions.get(5), positions.get(7)) );
        positionListHashMap.put(positions.get(6), Arrays.asList(positions.get(4), positions.get(6)) );
        positionListHashMap.put(positions.get(7), Arrays.asList(positions.get(6), positions.get(0)) );


        startPosition = positions.get(0);

         */

        return positionListHashMap;
    }

    /**
     * Returns a list of positions with length n
     * @param n is the length of the list
     * @return a list of IPositions with length n
     */
    public static List<IPosition> makePositions(int n) {
        List<IPosition> positions = new ArrayList<>();


        /*

        positions.add(new NormalPosition(100, 100));
        positions.add(new NormalPosition(100, 200));
        positions.add(new NormalPosition(100, 300));
        positions.add(new NormalPosition(200, 100));
        positions.add(new NormalPosition(200, 300));
        positions.add(new NormalPosition(300, 100));
        positions.add(new NormalPosition(300, 200));
        positions.add(new NormalPosition(300, 300));

         */

        return positions;
    }

    /**
     *
     * @param jsonArray
     * @return a list with IPositions
     */
    public static List<IPosition> makePositions(JSONArray jsonArray) {
        List<IPosition> positions = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject current = jsonArray.getJSONObject(i);
            positions.add(new NormalPosition(current.getJSONObject("position").getInt("positionID"),
                    current.getJSONObject("position").getInt("X"), current.getJSONObject("position").getInt("Y")));
        }

        return positions;
    }

}
