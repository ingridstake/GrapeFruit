package main.java.edu.chalmers.tda367.project.Model.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PositionFactory {

    public static IPosition startPosition;

    /**
     *
     */

    public static HashMap<Integer, List<IPosition>> makePositions() {

        HashMap<Integer, List<IPosition>> positionListHashMap = new HashMap<>();

        List<IPosition> positions = new ArrayList<>();


        for(int i = 0; i < 8; i++){
            positions.add(new NormalPosition(i));
        }
        positionListHashMap.put(0, Arrays.asList(positions.get(7), positions.get(1)) );
        positionListHashMap.put(1, Arrays.asList(positions.get(0), positions.get(2)) );
        positionListHashMap.put(2, Arrays.asList(positions.get(1), positions.get(3)) );
        positionListHashMap.put(3, Arrays.asList(positions.get(2), positions.get(4)) );
        positionListHashMap.put(4, Arrays.asList(positions.get(3), positions.get(5)) );
        positionListHashMap.put(5, Arrays.asList(positions.get(5), positions.get(7)) );
        positionListHashMap.put(6, Arrays.asList(positions.get(4), positions.get(6)) );
        positionListHashMap.put(7, Arrays.asList(positions.get(6), positions.get(0)) );

        startPosition = positions.get(0);



        return positionListHashMap;
    }


}
