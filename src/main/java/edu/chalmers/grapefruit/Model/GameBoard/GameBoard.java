package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Utils.IPositionable;
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
    List<IPositionable> positionableList;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap("edu/chalmers/grapefruit/Model/Gameboard.json");

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
     * Moves the player and updates its position if the new position is a valid move.
     * Then the all positions on the map are dehighlighted.
     * @param newNode is the new position.
     */
    public void movePlayer(Node newNode, IPlayer player){
        List<Node> validMoves = new ArrayList<>();
        playerPositionHashMap.get(player).evaluateValidMoves(validMoves, 3);

        if (validMoves.contains(newNode)){
            playerPositionHashMap.replace(currPlayer, newNode);
            currPlayer.updatePlayerPosition(newNode.getPosition().getX(), newNode.getPosition().getY());
        }

        map.deHighlight();

        if (playerPositionHashMap.get(player).getPosition().getClass().toString().equals("CityPosition")){
            System.out.println("yey");
        }
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