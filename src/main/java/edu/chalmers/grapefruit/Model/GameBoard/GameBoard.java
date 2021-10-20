package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Dice;
import edu.chalmers.grapefruit.Model.PlayerCardResourceFactory;
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

    private HashMap<IPlayer, Node> playerPositionHashMap;
    private Map map;
    private Dice dice;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap("edu/chalmers/grapefruit/Model/Gameboard.json");

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            IPosition position = playerPositionHashMap.get(player).getPosition();
            player.updatePlayerPosition(position.getPoint().x, position.getPoint().y);
        }

        createViewEntities();
        createPlayerCardResources();

        this.dice = new Dice(6);
    }

    /**
     * Add all view elements to the viewEntityFactory.
     */
    private void createViewEntities(){

        for (Node node :  map.getAllNodes()) {
            ViewEntityFactory.addEntity(node.getPosition());
        }
        playerPositionHashMap.forEach((k,v) -> ViewEntityFactory.addEntity(k));
    }

    /**
     * Add all players to the playerCardResourceFactory.
     */
    private void createPlayerCardResources() {
        playerPositionHashMap.forEach((k,v) -> PlayerCardResourceFactory.addPlayerCardResource(k));
    }

    /**
     * Gives the current player the position with the current values of x and y.
     * @param x is the x coordinate of the new position.
     * @param y is the y coordinate of the new position.
     * @param player is the player that is moved.
     */
    public Node movePlayer(int x, int y, IPlayer player) {
        for (Node node : map.getAllNodes()) {
            if (x == node.getPosition().getPoint().x && y == node.getPosition().getPoint().y) {
                movePlayer(node, player);
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
    private void movePlayer(Node newNode, IPlayer player){
        List<Node> validMoves = new ArrayList<>();
        playerPositionHashMap.get(player).evaluateValidMoves(validMoves, dice.getValue() + 1);

        if (validMoves.contains(newNode)){
            playerPositionHashMap.replace(player, newNode);
            player.updatePlayerPosition(newNode.getPosition().getPoint().x, newNode.getPosition().getPoint().y);
        }

        map.deHighlight();
    }

    /**
     * Rolls dice and then evaluates which moves are valid and highlights them.
     */
    public void makeDiceRoll(IPlayer player){
        playerPositionHashMap.get(player).evaluateValidMoves(new ArrayList<>(), dice.roll());
    }

    /**
     * Returns the corresponding node from the player position map.
     * @param player is the player whose node is wanted.
     * @return the corresponding node.
     */
    public Node getNode(IPlayer player) {
        return playerPositionHashMap.get(player);
    }
}