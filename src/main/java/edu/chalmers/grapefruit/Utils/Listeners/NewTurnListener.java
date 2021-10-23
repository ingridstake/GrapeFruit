package edu.chalmers.grapefruit.Utils.Listeners;

/**
 * @author Elvina Fahlgren
 */
public interface NewTurnListener {
    /**
     * Updates the current player id.
     * @param currentPlayerId is the new current players id
     */
    void newTurn(int currentPlayerId);
}
