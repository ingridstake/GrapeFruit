package main.java.edu.chalmers.tda367.project.Model.Player;
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

    @Override
    public PlayerColor getPlayerColor() {
        return PLAYER_COLOR;
    }
}
