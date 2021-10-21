package edu.chalmers.grapefruit.Model;

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
}