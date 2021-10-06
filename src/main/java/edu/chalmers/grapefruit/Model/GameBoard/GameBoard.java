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
        }

        gatherPositionableList();
    }

    // TODO: Borde även inkludera spelare
    private void gatherPositionableList(){
        positionableList = new ArrayList<>();

        for (Node node : getMap().getAllNodes()) {
            positionableList.add(node.getPosition());
        }
    }

    private Map getMap() {
        return map;
    }

    public List<IPositionable> getPositionableList() {
        return positionableList;
    }

    public void movePlayer(int x, int y) {
        for (Node node :
                map.getAllNodes()) {
            if (x == node.getPosition().getX() && y == node.getPosition().getY()) {
                movePlayer(node);
                break;
            }

        }
    }

    private void movePlayer(Node newNode){
        playerPositionHashMap.replace(currPlayer, newNode);
    }
}