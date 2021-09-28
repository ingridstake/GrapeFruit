package edu.chalmers.grapefruit.Model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        List<IPosition> positions = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!(i == 2 && j==2)){
                    positions.add(new NormalPosition((i+1)*20, (j+1)*20));
                }
            }
        }
        positionListHashMap.put(positions.get(0), Arrays.asList(positions.get(7), positions.get(1)) );
        positionListHashMap.put(positions.get(1), Arrays.asList(positions.get(0), positions.get(2)) );
        positionListHashMap.put(positions.get(2), Arrays.asList(positions.get(1), positions.get(3)) );
        positionListHashMap.put(positions.get(3), Arrays.asList(positions.get(2), positions.get(4)) );
        positionListHashMap.put(positions.get(4), Arrays.asList(positions.get(3), positions.get(5)) );
        positionListHashMap.put(positions.get(5), Arrays.asList(positions.get(5), positions.get(7)) );
        positionListHashMap.put(positions.get(6), Arrays.asList(positions.get(4), positions.get(6)) );
        positionListHashMap.put(positions.get(7), Arrays.asList(positions.get(6), positions.get(0)) );

        startPosition = positions.get(0);

        return positionListHashMap;
    }

    /**
     * Returns a list of positions with length n
     * @param n is the length of the list
     * @return a list of IPositions with length n
     */
    public static List<IPosition> makePositions(int n) {
        List<IPosition> positions = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!(i == 2 && j==2)){
                    positions.add(new NormalPosition((i+1)*20, (j+1)*20));
                }
            }
        }
        return positions;
    }
}
