package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Player.Player;
import edu.chalmers.grapefruit.Model.PlayerCardResourceFactory;
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

    List<IPlayer> playerList;
    HashMap<IPlayer, Node> playerPositionHashMap;
    Map map;
    CurrentPlayer currentPlayer;

    public GameBoard(List<IPlayer> players){
        playerList = players;
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap("edu/chalmers/grapefruit/Model/Gameboard.json");

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            IPosition position = playerPositionHashMap.get(player).getPosition();
            player.updatePlayerPosition(position.getPoint().x, position.getPoint().y);
        }

        currentPlayer = new CurrentPlayer(players.get(0));

        createViewEntities();
        createPlayerCardResources();
    }

    private void createViewEntities(){

        for (Node node :  map.getAllNodes()) {
            ViewEntityFactory.addEntity(node.getPosition());
        }
        playerPositionHashMap.forEach((k,v) -> ViewEntityFactory.addEntity(k));
    }

    private void createPlayerCardResources() {
        playerPositionHashMap.forEach((k,v) -> PlayerCardResourceFactory.addPlayerCardResource(k));
    }

    /**
     * Gives the current player the position with the current values of x and y.
     * @param x is the x coordinate of the new position.
     * @param y is the y coordinate of the new position.
     */
    public void movePlayer(int x, int y) {
        for (Node node : map.getAllNodes()) {
            if (x == node.getPosition().getPoint().x && y == node.getPosition().getPoint().y) {
                movePlayer(node, currentPlayer.getCurrentPlayer());
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
            playerPositionHashMap.replace(currentPlayer.getCurrentPlayer(), newNode);
            currentPlayer.getCurrentPlayer().updatePlayerPosition(newNode.getPosition().getPoint().x, newNode.getPosition().getPoint().y);
        }

        map.deHighlight();

        GameLogic.executeGameLogic(player, newNode);
        currentPlayer.setNewCurrentPlayer(getNextPlayer(currentPlayer.getCurrentPlayer()));
    }

    /**
     * Iterates through the list of players to find the next player in the list from the referencePlayer.
     * @param referencePlayer is the player the next player is evaluated from.
     * @return the IPlayer that is next in the playerList.
     */
    private IPlayer getNextPlayer(IPlayer referencePlayer) {
        boolean referencePlayerIsFound = false;

        for (int i = 1; i <= playerList.size(); i++) {
            if (referencePlayerIsFound) {
                return playerList.get(i-1);
            }

            if (playerList.get(i-1) == referencePlayer) {
                referencePlayerIsFound = true;
            }
            if (i >= playerList.size()) {
                i = 0;
            }
        }

        return referencePlayer;
    }

    /**
     * Rolls dice and then evaluates which moves are valid and highlights them.
     */
    public void makeDiceRoll(){
        int dice = rollDice();
        playerPositionHashMap.get(currentPlayer.getCurrentPlayer()).evaluateValidMoves(new ArrayList<>(), dice);
    }

    /**
     * Returns the current player of the game.
     * @return the current player of the game.
     */
    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private int rollDice(){
        return 2;
    }
}