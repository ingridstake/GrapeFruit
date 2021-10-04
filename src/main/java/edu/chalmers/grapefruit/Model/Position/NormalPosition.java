package edu.chalmers.grapefruit.Model.Position;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class NormalPosition implements IPosition{


    private final int ID;
    private final int X;
    private final int Y;
    boolean isHighlighted;

    protected NormalPosition(int id, int x, int y){
        ID = id;
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

    @Override
    public int getID() { return ID; }

    @Override
    public String resourceString() {
        return "node-view.fxml";
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
