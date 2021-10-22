package edu.chalmers.grapefruit.Utils;

import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.Player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 */
public class PlayerCardResourceFactory {

    private static List<PlayerCardResource> playerCardResources = new ArrayList<>();

    /**
     * Adds the player to the list of playerCardResources.
     * @param player is the player to add to the list.
     */
    public static void addPlayerCardResource(IPlayer player) {
        playerCardResources.add(new PlayerCardResource(player));
    }

    /**
     * Returns the playerCardResourceList.
     * @return the playerCardResourceList.
     */
    public static List<PlayerCardResource> getPlayerCardResources() {
        return playerCardResources;
    }

    /**
     * Adds players from game model to a list of player card resources.
     * @param gameModel is the game model.
     * @return list of player card resources.
     */
    public static List<PlayerCardResource> createPlayerCardResources(GameModel gameModel){
        for (IPlayer player : gameModel.getPlayers()) {
            addPlayerCardResource(player);
        }
        return playerCardResources;
    }
}