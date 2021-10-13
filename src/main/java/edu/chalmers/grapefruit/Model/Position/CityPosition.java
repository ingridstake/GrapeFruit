package edu.chalmers.grapefruit.Model.Position;

/**
 * @author tovenilsson
 */

public class CityPosition implements IPosition{

    private final int X;
    private final int Y;
    boolean isHighlighted;

    protected CityPosition(int x, int y){
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
    public String getResourceString() {
        if (isHighlighted){
            return "cityPosition-highlighted-view.fxml";
        }
        return "cityPosition-view.fxml";
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
    public void deHighlight() {
        isHighlighted = false;
    }
}