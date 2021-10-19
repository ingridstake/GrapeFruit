package edu.chalmers.grapefruit.Model.Player;

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

    /**
     * Evaluate the player color and match it with the right view-resource.
     * @param PLAYER_COLOR is the color of the player.
     * @return the view-resource.
     */
    public static String evaluateResourceString(PlayerColor PLAYER_COLOR){
        String resourceString;
        switch (PLAYER_COLOR) {
            case PINK:
                resourceString = "pink-player-view.fxml";
                break;
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

    public static String evaluateCardResourceString(PlayerColor playerColor) {
        switch (playerColor) {
            case PINK:
                return "pink-player-card.fxml";
            case PURPLE:
                return "purple-player-card.fxml";
            case YELLOW:
                return "yellow-player-card.fxml";
            case TURQUOISE:
                return "turquoise-player-card.fxml";
            default:
                return null;
        }
    }
}

