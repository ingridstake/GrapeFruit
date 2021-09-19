package main.java.edu.chalmers.tda367.project.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class PlayerFactory {

    /**
     * Creates a list of Players assigned with different colors
     * @param n is the desired number of players in the list, n greater than 4 is not allowed.
     * @return a list with length n containing players
     */
    public static List<Player> MakePlayers(int n){
        if (n>4){
            return null;
        }

        List<PlayerColor> colorList = getPlayerColors();

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i<n; i++){
            playerList.add(new Player(colorList.get(i)));
        }
        return playerList;
    }

    private static List<PlayerColor> getPlayerColors() {
        List<PlayerColor> colorList = new ArrayList<>();
        for (PlayerColor color: PlayerColor.values()) {
            colorList.add(color);
        }
        return colorList;
    }
}
