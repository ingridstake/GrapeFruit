package edu.chalmers.grapefruit.Model.Player;

import edu.chalmers.grapefruit.Utils.IPositionable;

/**
     * @author ingrid.stake
     * @author tove.nilsson
     * @author elvina.fahlgren
     * @author olivia.månström
     */
public interface IPlayer extends IPositionable {
    /**
     * Returns the color of the player.
     * @return the color of the player.
     */
    PlayerColor getPlayerColor();

    /**
     * Updates the position of the player.
     * @param x is the x-value of the position.
     * @param y is the y-value of the position.
     */
    void updatePlayerPosition(int x, int y);

    /**
     * Sets hasCow to true if player found the cow.
     */
    void playerFoundCow();
    /**
     * Sets hasVisum to true if player found a visum.
     */
    void playerFoundVisa();
    boolean hasCow();
    boolean hasVisa();
}

