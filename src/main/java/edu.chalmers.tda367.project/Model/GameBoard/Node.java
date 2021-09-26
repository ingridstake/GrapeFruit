package main.java.edu.chalmers.tda367.project.Model.GameBoard;

import main.java.edu.chalmers.tda367.project.Model.Position.IPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class Node {
    List<Node> relatedNodes;
    private final IPosition position;


    public Node(IPosition position) {
        this.position = position;
        relatedNodes = null;
    }

    public Node(IPosition position, List<Node> relatedNodes) {
        this.position = position;
        this.relatedNodes = relatedNodes;
    }

    /**
     * Adds a node to the list of related nodes
     * @param node is the node that is added to the list
     */
    public void addRelatedNode(Node node) {
        if (relatedNodes == null){
            relatedNodes = new ArrayList<>();
        }
        relatedNodes.add(node);
    }

    /**
     * Adds nodes from a list to the list of related nodes
     * @param nodes is the list of nodes thar is added
     */
    public void addRelatedNode(List<Node> nodes) {
        for (Node node : nodes) {
            addRelatedNode(node);
        }
    }

    /**
     * Returns the list of related nodes
     * @return the list of related nodes
     */
    public List<Node> getNeighbours(){
        return relatedNodes;
    }

    /**
     * Returns a list of all related nodes besides the previous node
     * @param previousNode is the related node that is not included in the returned list
     * @return The list of all related nodes but the previousNode
     */
    public List<Node> getNeighbours(Node previousNode){
        List<Node> nodeList = new ArrayList<>();
        for (Node node : relatedNodes) {
            if (node != previousNode) {
                nodeList.add(node);
            }
        }
        return nodeList;
    }

    /**
     * Returns the position of the node
     * @return thw position of the node
     */
    public IPosition getPosition(){
        return position;
    }
}
