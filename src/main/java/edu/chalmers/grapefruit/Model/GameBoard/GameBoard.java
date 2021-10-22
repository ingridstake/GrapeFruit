package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Dice;
import edu.chalmers.grapefruit.Utils.PlayerCardResourceFactory;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Utils.ViewEntityFactory;
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

    private HashMap<IPlayer, Node> playerPositionHashMap;
    private Map map;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap("edu/chalmers/grapefruit/Model/Gameboard.json");

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            IPosition position = playerPositionHashMap.get(player).getPosition();
            player.updatePlayerPosition(position.getPoint().x, position.getPoint().y);
        }
    }

    /**
     * Gives the current player the position with the current values of x and y.
     * @param x is the x coordinate of the new position.
     * @param y is the y coordinate of the new position.
     * @param player is the player that is moved.
     */
    public Node movePlayer(int x, int y, IPlayer player, int steps) {
        for (Node node : map.getAllNodes()) {
            if (x == node.getPosition().getPoint().x && y == node.getPosition().getPoint().y) {
                movePlayer(node, player, steps);
                return node;
            }
        }
        return null;
    }

    /**
     * Moves the player and updates its position if the new position is a valid move.
     * Then the all positions on the map are dehighlighted.
     * @param newNode is the new position.
     * @param player is the player that is moved.
     */
    private void movePlayer(Node newNode, IPlayer player, int steps){
        List<Node> validMoves = new ArrayList<>();
        playerPositionHashMap.get(player).evaluateValidMoves(validMoves, steps);

        if (validMoves.contains(newNode)){
            playerPositionHashMap.replace(player, newNode);
            player.updatePlayerPosition(newNode.getPosition().getPoint().x, newNode.getPosition().getPoint().y);
        }

        map.deHighlight();
    }

    /**
     * Rolls dice and then evaluates which moves are valid and highlights them.
     */
    public void makeDiceRoll(IPlayer player, int diceValue){
        playerPositionHashMap.get(player).evaluateValidMoves(new ArrayList<>(), diceValue);
    }

    /**
     * Returns the corresponding node from the player position map.
     * @param player is the player whose node is wanted.
     * @return the corresponding node.
     */
    public Node getNode(IPlayer player) {
        return playerPositionHashMap.get(player);
    }

    public List<IPosition> getPositionList(){
        List<IPosition> positionList = new ArrayList<>();
        for (Node node: map.getAllNodes()) {
            positionList.add(node.getPosition());
        }
        return positionList;
    }
}