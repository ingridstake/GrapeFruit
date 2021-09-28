package edu.chalmers.grapefruit.Model.Position;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class NormalPosition implements IPosition{

    private final int X;
    private final int Y;
    boolean isHighlighted;

    protected NormalPosition(int x, int y){
        X = x;
        Y = y;
        isHighlighted = false;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
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
}
