package edu.chalmers.grapefruit.Utils.Listeners;

/**
 * @author Elvina Fahlgren
 *
 */
public interface DiceRolledListener {
    /**
     * Updates the dice value.
     * @param diceValue the current rolled dice value
     */
    void updateDiceValue(int diceValue);
}
