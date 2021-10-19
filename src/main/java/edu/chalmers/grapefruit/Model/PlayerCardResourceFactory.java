package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.Player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerCardResourceFactory {

    private static List<PlayerCardResource> playerCardResources = new ArrayList<>();

    public static void addPlayerCardResource(IPlayer player) {
        playerCardResources.add(new PlayerCardResource(player));
    }

    public static List<PlayerCardResource> getPlayerCardResources() {
        return playerCardResources;
    }
}
