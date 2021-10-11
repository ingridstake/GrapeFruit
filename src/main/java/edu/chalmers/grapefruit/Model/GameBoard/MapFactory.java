package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.PositionFactory;
import edu.chalmers.grapefruit.Model.Json.JsonHandler;
import edu.chalmers.grapefruit.Model.Json.JsonMap;
import edu.chalmers.grapefruit.Model.Json.JsonNeighbour;
import edu.chalmers.grapefruit.Model.Json.JsonPosition;

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
     * Uses JsonHandler to create a map based on a json file.
     * The json file must be structured in the following way:
     *      - Have a JSONArray named "PositionList" which contains
     *              - int positionID
     *              - String positionType
     *              - int X
     *              - int Y
     *      - Have a JSONObject named "Neighbours" which contains
     *              - int id
     *              - List<Integer> neighbours
     * Creates Nodes and connect neighbours to each node.
     * @return a Map object
     */
    static Map createMap(int nNodes) {
        MapFactory app = new MapFactory();

        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
        JsonMap jsonMap = handler.getJsonMap();

        List<Node> nodes = app.createNodes(jsonMap.PositionList);

        Map map = new Map(nodes.get(0));
        for(JsonNeighbour temp : jsonMap.Neighbours){
            List<Node> neighbours = new ArrayList<>();
            for(int i : temp.neigbours) neighbours.add(nodes.get(i));
            map.add(nodes.get(temp.id), neighbours);
        }

        return map;
    }

    /**
     * Creates a list of Nodes based on a list of JsonPositions.
     * Uses PositionFactory to to make positions.
     * @param JsonPos is the list that contains information about each IPosition.
     * @return a list of all Nodes.
     */
    private List<Node> createNodes(List<JsonPosition> JsonPos) {
        List<IPosition> positions = PositionFactory.makePositions(JsonPos);
        List<Node> nodes = new ArrayList<>();
        for (IPosition position : positions) {
            nodes.add(new Node(position));
        }
        return nodes;
    }
}