package edu.chalmers.grapefruit.Utils;

import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerColor;

/**
 * @author ingrid.stake
 */

public class PlayerCardResource {

    //TODO: make private
    IPlayer playerResource;

    public PlayerCardResource(IPlayer player) {
        this.playerResource = player;
    }

    /**
     * Returns the money balance of the playerResource.
     * @return the money Balance of the playerResource.
     */
    public int getMoneyBalance() {
        return playerResource.getMoneyBalance();
    }

    /**
     * Returns true if the player resource has found the cow.
     * @return true if the player resource has found the cow.
     */
    public boolean hasCow() {
        return playerResource.hasCow();
    }

    /**
     * Returns true if the player resource has found the visa.
     * @return true if the player resource has found the visa.
     */
    public boolean hasVisa() {
        return playerResource.hasVisa();
    }

    /**
     * Returns true if player returns to start position and has cow or visa.
     * @return true if player returns to start position and has cow or visa.
     */
    public boolean isWinner(){
        return playerResource.isWinner();
    }

    /**
     * Evaluates and returns the resource string of the player resource's color.
     * @return the resource string of the player resource's color.
     */
    public String getResourceString() {
        return PlayerColor.evaluateCardResourceString(playerResource.getPlayerColor());
    }

    /**
     * Returns true if the parameter holds the same player resource.
     * @param currentPlayerId is the CurrentPlayer that is compared.
     * @return true if the parameter holds the same player resource.
     */
    public boolean hasSamePlayer(int currentPlayerId) {
        if (currentPlayerId == this.playerResource.getId()) {
            return true;
        }
        return false;
    }
}