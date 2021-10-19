package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.ViewEntityFactory;
import edu.chalmers.grapefruit.Model.Player.IPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class GameBoard {

    HashMap<IPlayer, Node> playerPositionHashMap;
    IPlayer currPlayer;
    Map map;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap("edu/chalmers/grapefruit/Model/Gameboard.json");

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            IPosition position = playerPositionHashMap.get(player).getPosition();
            player.updatePlayerPosition(position.getPoint().x, position.getPoint().y);
        }
        currPlayer = players.get(0);

        createViewEntities();
    }

    private void createViewEntities(){

        for (Node node :  map.getAllNodes()) {
            ViewEntityFactory.addEntity(node.getPosition());
        }
        playerPositionHashMap.forEach((k,v) -> ViewEntityFactory.addEntity(k));
    }

    /**
     * Gives the current player the position with the current values of x and y.
     * @param x is the x coordinate of the new position.
     * @param y is the y coordinate of the new position.
     */
    public void movePlayer(int x, int y) {
        for (Node node : map.getAllNodes()) {
            if (x == node.getPosition().getPoint().x && y == node.getPosition().getPoint().y) {
                movePlayer(node, currPlayer);
                break;
            }
        }
    }

    /**
     * Moves the player and updates its position if the new position is a valid move.
     * Then the all positions on the map are dehighlighted.
     * @param newNode is the new position.
     */
    public void movePlayer(Node newNode, IPlayer player){
        List<Node> validMoves = new ArrayList<>();
        playerPositionHashMap.get(player).evaluateValidMoves(validMoves, 3);

        if (validMoves.contains(newNode)){
            playerPositionHashMap.replace(currPlayer, newNode);
            currPlayer.updatePlayerPosition(newNode.getPosition().getPoint().x, newNode.getPosition().getPoint().y);
        }

        map.deHighlight();

        GameLogic.executeGameLogic(player, newNode);
    }

    /**
     * Rolls dice and then evaluates which moves are valid and highlights them.
     */
    public void makeDiceRoll(){
        int dice = rollDice();
        playerPositionHashMap.get(currPlayer).evaluateValidMoves(new ArrayList<>(), dice);

    }

    private int rollDice(){
        return 2;
    }
}