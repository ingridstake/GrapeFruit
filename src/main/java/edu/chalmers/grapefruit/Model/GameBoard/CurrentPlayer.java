package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Player.IPlayer;

/**
 * @author ingrid.stake
 */

public class CurrentPlayer {

    IPlayer currentPlayer;

    public CurrentPlayer(IPlayer firstCurrentPlayer) {
        currentPlayer = firstCurrentPlayer;
    }

    /**
     * Sets the currentPlayers player to the newCurrentPlayer.
     * @param newCurrentPlayer is the new current player.
     */
    public void setNewCurrentPlayer(IPlayer newCurrentPlayer) {
        currentPlayer = newCurrentPlayer;
    }

    /**
     * Returns the player that is the current player.
     * @return the IPlayer that is the current player.
     */
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean currentPlayerHasMoneyToTurnTile() {
        if (currentPlayer != null && currentPlayer.getMoneyBalance() >= 1000){
            return true;
        }
        return false;
    }
}