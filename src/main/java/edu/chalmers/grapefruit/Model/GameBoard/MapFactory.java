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

    //TODO TA BORT nNodes parameter!

    /**
     * Creates a map based on a JSON file.
     * The JSON file must be structured in the following way:
     *      - Have a JSONArray named "PositionList" that the class PositionFactory can make IPositions from.
     *      - Have a JSONObject named "Neighbours" which contains JSONArrays that holds the neighbours for every
     *      Node, using numbers.
     * @return
     */
    static Map createMap(int nNodes) {
        MapFactory app = new MapFactory();
        // Transforms input for reading
        JSONTokener tokener = new JSONTokener(app.getJSONFile());
        JSONObject object = new JSONObject(tokener);

        List<Node> nodes = app.createNodes(object.getJSONArray("PositionList"));

        JSONObject jsonNeighbours = object.getJSONObject("Neighbours");

        Map map = new Map(nodes.get(0));

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


    /**
     * Returns the stream that holds the needed json file which sets up the game's board.
     * Finds the json file using the right path.
     * If the json file couldn't be found, the method will throw an IllegalArgumentException.
     * @return the stream that holds the json file "board.json"
     */
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

    /**
     * Creates Nodes using a JSONArray with JSONObjects in it. Each JSONObject holds information about each IPosition
     * which is submitted as an argument to create a Node.     *
     * @param jsonPositionArray is the JSONOArrays that contains information about each IPosition.
     * @return a List of all Nodes.
     */
    private List<Node> createNodes(JSONArray jsonPositionArray) {
        List<IPosition> positions = PositionFactory.makePositions(jsonPositionArray);
        List<Node> nodes = new ArrayList<>();
        for (IPosition position : positions) {
            nodes.add(new Node(position));
        }
        return nodes;
    }
}

