package edu.chalmers.grapefruit.Model.GameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class Map {

    private final Node startNode;
    private int size;

    public Map(Node startNode){
        this.startNode = startNode;
    }

    public Node getStartNode(){
        return startNode;
    }

    /**
     * Adds a node to the map structure
     * @param node is the new node that is integrated in the map.
     * @param relatedNodes is the related nodes that need to be connected with the new node.
     */
    protected void add (Node node, List<Node> relatedNodes){
        for (Node relatedNode : relatedNodes) {
            relatedNode.addRelatedNode(node);
        }
        node.addRelatedNode(relatedNodes);
        size++;
    }

    /**
     * Returns a list containing all nodes of the map.
     * @return a List of Nodes.
     */
    protected List<Node> getAllNodes(){
        List<Node> allNodes = new ArrayList<>();
        return startNode.getAllNodes(allNodes);
    }

    public int getSize() {
        return size;
    }

    /**
     * Dehighlights all nodes in the map
     */
    public void deHighlightAllNodes() {
        for (Node node : getAllNodes()) {
            node.getPosition().deHighlight();
        }
    }
}