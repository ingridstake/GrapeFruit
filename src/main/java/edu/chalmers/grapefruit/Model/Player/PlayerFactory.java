package edu.chalmers.grapefruit.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class PlayerFactory {

    /**
     * Creates a list of Players assigned with different colors
     * @param n is the desired number of players in the list, n greater than 4 is not allowed.
     * @return a list with length n containing players
     */
    public static List<IPlayer> MakePlayers(int n){

        List<PlayerColor> colorList = PlayerColor.getPlayerColors();

        List<IPlayer> playerList = new ArrayList<>();
        for (int i = 0; i<n; i++){
            playerList.add(new Player(colorList.get(i), i));
        }
        return playerList;
    }
}