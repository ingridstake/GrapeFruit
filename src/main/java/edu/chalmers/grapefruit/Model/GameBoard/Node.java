package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Position.IPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class Node {
    private List<Node> relatedNodes;
    private final IPosition position;

    public Node(IPosition position) {
        this.position = position;
        relatedNodes = null;
    }

    /**
     * Adds a node to the list of related nodes
     * @param node is the node that is added to the list
     */
    protected void addRelatedNode(Node node) {
        if (relatedNodes == null){
            relatedNodes = new ArrayList<>();
        }
        relatedNodes.add(node);
    }

    /**
     * Adds nodes from a list to the list of related nodes
     * @param nodes is the list of nodes thar is added
     */
    protected void addRelatedNode(List<Node> nodes) {
        for (Node node : nodes) {
            addRelatedNode(node);
        }
    }

    /**
     * Returns the list of related nodes
     * @return the list of related nodes
     */
    protected List<Node> getNeighbours(){
        return relatedNodes;
    }

    /**
     * Returns the position of the node
     * @return thw position of the node
     */
    public IPosition getPosition(){
        return position;
    }

    protected List<Node> getAllNodes(List<Node> nodes) {
        for (Node node : this.getNeighbours()) {
            if (!nodes.contains(node)){
                nodes.add(node);
                node.getAllNodes(nodes);
            }
        }
        return nodes;
    }

    /**
     * Checks if current node is within range of the dice and then does the same with its neighbours.
     * @param validMoves is the list of nodes all valid move-nodes are added to.
     * @param diceValue is the valid move range based on the dice.
     */
    protected void evaluateValidMoves(List<Node> validMoves, int diceValue) {
        if(diceValue >= 0 && !validMoves.contains(this)) {
            validMoves.add(this);
            position.highlight();
            for (Node node : relatedNodes) {
                node.evaluateValidMoves(validMoves, diceValue - 1);
            }
        }
    }
}