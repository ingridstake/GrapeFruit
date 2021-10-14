package edu.chalmers.grapefruit.Model.Position;

/**
 * @author tovenilsson
 */

public class StartPosition implements IPosition{

    private final int X;
    private final int Y;
    boolean isHighlighted;

    protected StartPosition(int x, int y){
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
            return "startPosition-highlighted-view.fxml";
        }
        return "startPosition-view.fxml";
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

    @Override
    public boolean isHighlighted() {
        return isHighlighted;
    }
}
