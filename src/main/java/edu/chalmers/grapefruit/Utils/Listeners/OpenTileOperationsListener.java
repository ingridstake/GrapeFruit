package edu.chalmers.grapefruit.Utils.Listeners;

/**
 * @author Elvina Fahlgren
 */
public interface OpenTileOperationsListener {
    /**
     * Updates the condition for if a tile can be opened using dice.
     * @param canRollDiceToOpenTile represents if the tile can be opened using dice
     */
    void updateDiceToOpenTile(boolean canRollDiceToOpenTile);

    /**
     * Updates the condition for if a tile can be opened using money.
     * @param canPayToOpenTile represents if the tile can be opened using money
     */
    void updatePayToOpenTile(boolean canPayToOpenTile);
}
