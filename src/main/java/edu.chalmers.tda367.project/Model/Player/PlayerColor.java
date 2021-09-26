package main.java.edu.chalmers.tda367.project.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public enum PlayerColor {
    PINK,PURPLE,TURQUOISE,YELLOW;

    /**
     * Returns a list of all different values of the enum PlayerColor.
     * @return a list of all different values of the enum PlayerColor
     */
    public static List<PlayerColor> getPlayerColors() {
        List<PlayerColor> colorList = new ArrayList<>();
        for (PlayerColor color: PlayerColor.values()) {
            colorList.add(color);
        }
        return colorList;
    }
}
