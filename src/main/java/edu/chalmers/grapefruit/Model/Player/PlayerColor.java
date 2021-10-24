package edu.chalmers.grapefruit.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
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

    /**
     * Evaluate the player color and match it with the right view-resource for a gam piece, if the player color is pink, the default resource string is returned.
     * @param playerColor is the color of the player.
     * @return the view-resource string.
     */
    public static String evaluateResourceString(PlayerColor playerColor){
        String resourceString;
        switch (playerColor) {
            case PURPLE:
                resourceString = "purple-player-view.fxml";
                break;
            case TURQUOISE:
                resourceString = "turquoise-player-view.fxml";
                break;
            case YELLOW:
                resourceString = "yellow-player-view.fxml";
                break;
            default:
                resourceString = "pink-player-view.fxml";
        }
        return resourceString;
    }

    /**
     * Evaluate the player color and match it with the right view-resource for a player card, if the player color is pink, the default resource string is returned.
     * @param playerColor is the color of the player.
     * @return the view-resource string.
     */
    public static String evaluateCardResourceString(PlayerColor playerColor) {
        switch (playerColor) {
            case PURPLE:
                return "purple-player-card.fxml";
            case YELLOW:
                return "yellow-player-card.fxml";
            case TURQUOISE:
                return "turquoise-player-card.fxml";
            default:
                return "pink-player-card.fxml";
        }
    }
}