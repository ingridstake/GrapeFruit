package edu.chalmers.grapefruit.Model.Player;

/**
 * The Player class contains the functionality of a Player.
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class Player implements IPlayer{
    private int moneyBalance;
    private boolean hasCow;
    private boolean hasVisa;
    private final PlayerColor PLAYER_COLOR;
    private int X;
    private int Y;
    private String resourceString;

    protected Player(PlayerColor playerColor){
        moneyBalance = 5000;
        hasCow = false;
        hasVisa = false;
        PLAYER_COLOR = playerColor;

        switch (PLAYER_COLOR) {
            case PINK:
                resourceString = "pink-player-view.fxml";
                break;
            case PURPLE:
                resourceString = "purple-player-view.fxml";
                break;
            case TURQUOISE:
                resourceString = "turquoise-player-view.fxml";
                break;
            case YELLOW:
                resourceString = "yellow-player-view.fxml";
                break;
            default:
                resourceString = "pink-player-view.fxml";
        }
    }

    /**
     * Sets hasCow to true if player found the cow.
     */
    public void playerFoundCow(){
        hasCow = true;
    }
    /**
     * Sets hasVisum to true if player found a visum.
     */
    public void playerFoundVisa(){
        hasVisa = true;
    }

    public boolean hasCow() {
        return hasCow;
    }

    public boolean hasVisa() {
        return hasVisa;
    }

    /**
     * Returns the color of the player
     * @return returns the color of the player
     */
    @Override
    public PlayerColor getPlayerColor() {
        return PLAYER_COLOR;
    }

    @Override
    public void updatePlayerPosition(int x, int y) {
        this.X = x;
        this.Y = y;
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
        return resourceString;
    }
}

