package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Interfaces.IPositionable;
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
        map = MapFactory.createMap(8);

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
            player.updatePlayerPosition(playerPositionHashMap.get(player).getPosition().getX(), playerPositionHashMap.get(player).getPosition().getY());
        }

        gatherPositionableList();
    }

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

    public void movePlayer(Node newNode){
        playerPositionHashMap.replace(currPlayer, newNode);
        currPlayer.updatePlayerPosition(newNode.getPosition().getX(), newNode.getPosition().getY());
    }
}