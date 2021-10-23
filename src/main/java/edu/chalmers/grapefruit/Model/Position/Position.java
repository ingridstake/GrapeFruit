package edu.chalmers.grapefruit.Model.Position;

import java.awt.*;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class Position implements IPosition{

    private Point point;
    boolean isHighlighted;
    private LogicType logicType;

    public Position(int x, int y){
        point = new Point(x, y);
        isHighlighted = false;
        this.logicType = LogicType.NONE;
    }

    public Position (int x, int y, LogicType logicType) {
        point = new Point(x, y);
        isHighlighted = false;
        this.logicType = logicType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public String getResourceString() {
         return LogicType.evaluatePositionResource(this.getLogicType(), isHighlighted);
    }

    /**
     * Changes the state of isHighlighted to true
     */
    @Override
    public void highlight() {
        isHighlighted = true;
    }

    /**
     * Changes the state of isHighlighted to false
     */
    @Override
    public void deHighlight(){
        isHighlighted = false;
    }

    @Override
    public boolean isHighlighted() {
        return isHighlighted;
    }

    @Override
    public LogicType getLogicType() {
        return logicType;
    }
}