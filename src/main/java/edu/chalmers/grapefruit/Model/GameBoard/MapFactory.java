package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.NormalPosition;
import edu.chalmers.grapefruit.Model.Position.PositionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class MapFactory {


    static Map createMap(int nNodes) {
        MapFactory app = new MapFactory();
        // Transforms input for reading
        JSONTokener tokener = new JSONTokener(app.getJSONFile());
        JSONObject object = new JSONObject(tokener);
        //System.out.println(object.getJSONArray("PositionList"));
        JSONArray jsonPositionArray = object.getJSONArray("PositionList");
        List<IPosition> positions = PositionFactory.makePositions(jsonPositionArray);

        JSONObject jsonNeighbours = object.getJSONObject("NeighboursHashMap");

        List<Node> nodes = new ArrayList<>();


        for (IPosition position : positions) {
            nodes.add(new Node(position));
        }

        Map map = new Map(nodes.get(0));
        /*
        map.add( nodes.get(0), Arrays.asList(nodes.get(7), nodes.get(1)));
        map.add( nodes.get(1), Arrays.asList(nodes.get(0), nodes.get(2)));
        map.add( nodes.get(2), Arrays.asList(nodes.get(1), nodes.get(3)));
        map.add( nodes.get(3), Arrays.asList(nodes.get(2), nodes.get(4)));
        map.add( nodes.get(4), Arrays.asList(nodes.get(3), nodes.get(5)));
        map.add( nodes.get(5), Arrays.asList(nodes.get(4), nodes.get(6)));
        map.add( nodes.get(6), Arrays.asList(nodes.get(5), nodes.get(7)));
        map.add( nodes.get(7), Arrays.asList(nodes.get(6), nodes.get(0)));

         */

        Iterator<?> keys = jsonNeighbours.keys();
        while(keys.hasNext()) {
            int key = Integer.valueOf((String)keys.next());
            JSONArray neighboursJSONArray = jsonNeighbours.getJSONArray(String.valueOf(key));
            List<Node> neighbours = new ArrayList<>();

            for(int i = 0; i < neighboursJSONArray.length(); i++){
                neighbours.add(nodes.get(neighboursJSONArray.getInt(i)));
            }
            map.add(nodes.get(key), neighbours);
        }

        return map;
    }

    private InputStream getJSONFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("edu/chalmers/grapefruit/Model/board.json");

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("JSON file not found!");
        } else {
            return inputStream;
        }
    }
}

