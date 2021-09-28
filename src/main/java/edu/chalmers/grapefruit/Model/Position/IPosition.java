package edu.chalmers.grapefruit.Model.Position;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public interface IPosition {

    int getX();
    int getY();
    void highlight();
    void deHighlight();
}
