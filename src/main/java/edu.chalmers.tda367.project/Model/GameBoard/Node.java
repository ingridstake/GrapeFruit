package main.java.edu.chalmers.tda367.project.Model.GameBoard;

import main.java.edu.chalmers.tda367.project.Model.Position.IPosition;

import java.util.ArrayList;
import java.util.List;

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

    public void addRelatedNode(Node node) {
        if (relatedNodes == null){
            relatedNodes = new ArrayList<>();
        }
        relatedNodes.add(node);
    }

    public void addRelatedNode(List<Node> nodes) {
        for (Node node : nodes) {
            addRelatedNode(node);
        }
    }

    public List<Node> getNeighbours(){
        return relatedNodes;
    }

    public List<Node> getNeighbours(Node previousNode){
        List<Node> nodeList = new ArrayList<>();
        for (Node node : relatedNodes) {
            if (node != previousNode) {
                nodeList.add(node);
            }
        }
        return nodeList;
    }

    public IPosition getPosition(){
        return position;
    }
}
