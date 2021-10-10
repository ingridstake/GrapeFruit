package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Interfaces.IPositionable;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Position.IPosition;

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
    List<IPositionable> positionableList;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap(8);

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            player.updatePlayerPosition(playerPositionHashMap.get(player).getPosition().getX(), playerPositionHashMap.get(player).getPosition().getY());
        }
        currPlayer = players.get(0);

        gatherPositionableList();
    }

    /**
     * Gather positionable objects as nodes and players to a list.
     */
    private void gatherPositionableList(){
        positionableList = new ArrayList<>();

        for (Node node : getMap().getAllNodes()) {
            positionableList.add(node.getPosition());
        }

        playerPositionHashMap.forEach((k,v) -> positionableList.add(k));
    }

    private Map getMap() {
        return map;
    }

    public List<IPositionable> getPositionableList() {
        return positionableList;
    }

    /**
     * Gives the current player the position with the current values of x and y.
     * @param x is the x coordinate of the new position.
     * @param y is the y coordinate of the new position.
     */
    public void movePlayer(int x, int y) {
        for (Node node : map.getAllNodes()) {
            if (x == node.getPosition().getX() && y == node.getPosition().getY()) {
                movePlayer(node, currPlayer);
                break;
            }
        }
    }

    /**
     * Moves the player and updates its position.
     * @param newNode is the new position.
     */
    public void movePlayer(Node newNode, IPlayer player){
        List<Node> validMoves = playerPositionHashMap.get(player).getValidMoves(new ArrayList<>(), 3);
        if (validMoves.contains(newNode)){
            playerPositionHashMap.replace(currPlayer, newNode);
            currPlayer.updatePlayerPosition(newNode.getPosition().getX(), newNode.getPosition().getY());
        }

        map.deHighlightAllNodes();
    }

    public void makeDiceRoll(){
        int dice = rollDice();
        List<Node> nodes = playerPositionHashMap.get(currPlayer).getValidMoves(new ArrayList<>(), dice);
        int i=0;

    }

    private int rollDice(){
        return 2;
    }
}