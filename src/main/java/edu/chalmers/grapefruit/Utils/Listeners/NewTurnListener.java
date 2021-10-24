package edu.chalmers.grapefruit.Utils.Listeners;

/**
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public interface NewTurnListener {

    /**
     * Updates the current player id.
     * @param currentPlayerId is the new current players id
     */
    void newPlayer(int currentPlayerId);

    /**
     * Notifies that it is a new turn.
     */
    void newTurn();
}
