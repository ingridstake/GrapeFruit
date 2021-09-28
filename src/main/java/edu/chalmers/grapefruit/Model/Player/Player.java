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
    private boolean hasVisum;
    private final PlayerColor PLAYER_COLOR;

    protected Player(PlayerColor playerColor){
        moneyBalance = 5000;
        hasCow = false;
        hasVisum = false;
        PLAYER_COLOR = playerColor;
    }

    /**
     * Returns the color of the player
     * @return returns the color of the player
     */
    @Override
    public PlayerColor getPlayerColor() {
        return PLAYER_COLOR;
    }
}

