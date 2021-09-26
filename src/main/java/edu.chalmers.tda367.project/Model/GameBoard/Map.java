package main.java.edu.chalmers.tda367.project.Model.GameBoard;

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

    protected Node getStartNode(){
        return startNode;
    }


}
