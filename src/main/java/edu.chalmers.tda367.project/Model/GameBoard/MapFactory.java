package main.java.edu.chalmers.tda367.project.Model.GameBoard;

import main.java.edu.chalmers.tda367.project.Model.Position.IPosition;
import main.java.edu.chalmers.tda367.project.Model.Position.PositionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class MapFactory {

    /**
     * Creates and returns a map with nNodes nodes including a startNode
     * @param nNodes
     * @return
     */
    static Map createMap(int nNodes) {
        List<IPosition> positions = PositionFactory.makePositions(nNodes);
        List<Node> nodes = new ArrayList<>();
        for (IPosition position : positions) {
            nodes.add(new Node(position));
        }
        Map map = new Map(nodes.get(0));
        nodes.get(0).addRelatedNode(Arrays.asList(nodes.get(7), nodes.get(1)));
        nodes.get(1).addRelatedNode(Arrays.asList(nodes.get(0), nodes.get(2)));
        nodes.get(2).addRelatedNode(Arrays.asList(nodes.get(1), nodes.get(3)));
        nodes.get(3).addRelatedNode(Arrays.asList(nodes.get(2), nodes.get(4)));
        nodes.get(4).addRelatedNode(Arrays.asList(nodes.get(3), nodes.get(5)));
        nodes.get(5).addRelatedNode(Arrays.asList(nodes.get(4), nodes.get(6)));
        nodes.get(6).addRelatedNode(Arrays.asList(nodes.get(5), nodes.get(7)));
        nodes.get(7).addRelatedNode(Arrays.asList(nodes.get(6), nodes.get(0)));

        return map;
    }
}
