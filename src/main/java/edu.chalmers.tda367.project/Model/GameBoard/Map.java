package main.java.edu.chalmers.tda367.project.Model.GameBoard;

public class Map {
    private final Node startNode;

    protected Map(Node startNode){
        this.startNode = startNode;
    }

    protected Node getStartNode(){
        return startNode;
    }


}
