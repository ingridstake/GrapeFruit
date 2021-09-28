package edu.chalmers.grapefruit.Model.GameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class Map {
    private final Node startNode;

    protected Map(Node startNode){
        this.startNode = startNode;
    }

    public Node getStartNode(){
        return startNode;
    }

    public List<Node> getAllNodes(){
        List<Node> allNodes = new ArrayList<>();
        return startNode.getAllNodes(allNodes);
    }


}

