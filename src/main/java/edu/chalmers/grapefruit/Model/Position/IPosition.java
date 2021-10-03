package edu.chalmers.grapefruit.Model.Position;

import edu.chalmers.grapefruit.Interfaces.IPositionable;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public interface IPosition extends IPositionable {

    int getX();
    int getY();
    void highlight();
    void deHighlight();
}
