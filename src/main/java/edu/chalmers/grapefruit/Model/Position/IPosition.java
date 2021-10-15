package edu.chalmers.grapefruit.Model.Position;

import edu.chalmers.grapefruit.Utils.ViewEntityResource;

import java.awt.*;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public interface IPosition extends ViewEntityResource {
    Point getPoint();
    void highlight();
    void deHighlight();
    boolean isHighlighted();
    LogicType getLogicType();
}