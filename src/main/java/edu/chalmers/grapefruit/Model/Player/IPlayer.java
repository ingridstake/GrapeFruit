package edu.chalmers.grapefruit.Model.Player;

import edu.chalmers.grapefruit.Interfaces.IPositionable;

/**
     * @author ingrid.stake
     * @author tove.nilsson
     * @author elvina.fahlgren
     * @author olivia.månström
     */
public interface IPlayer extends IPositionable {
    PlayerColor getPlayerColor();

    void updatePlayerPosition(int x, int y);
}

