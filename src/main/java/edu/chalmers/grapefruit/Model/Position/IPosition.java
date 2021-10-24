package edu.chalmers.grapefruit.Model.Position;

import edu.chalmers.grapefruit.Utils.ViewEntityResource;

import java.awt.*;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public interface IPosition extends ViewEntityResource {

    Point getPoint();
    void highlight();
    void deHighlight();
    boolean isHighlighted();
    LogicType getLogicType();
}