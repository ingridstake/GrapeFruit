package edu.chalmers.grapefruit.Utils.Listeners;

/**
 * @author Elvina Fahlgren
 */
public interface NewTurnListener {
    /**
     * Sets the new current player id.
     * @param currentPlayerId is the new current players id
     */
    void newTurn(int currentPlayerId);
}
