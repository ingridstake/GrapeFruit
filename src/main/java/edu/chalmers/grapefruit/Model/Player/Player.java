package edu.chalmers.grapefruit.Model.Player;

import java.awt.*;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class Player implements IPlayer {

    private int moneyBalance;
    private boolean hasCow;
    private boolean hasVisa;
    private final PlayerColor PLAYER_COLOR;
    private Point point;
    private boolean isWinner;
    private int id;

    public Player(PlayerColor playerColor, int id) {
        moneyBalance = 3000;
        point = new Point(0,0);
        hasCow = false;
        hasVisa = false;
        isWinner = false;
        PLAYER_COLOR = playerColor;
        this.id = id;
    }

    /**
     * Sets hasCow to true if player found the cow.
     */
    @Override
    public void playerFoundCow(){
        hasCow = true;
    }

    /**
     * Sets hasVisum to true if player found a visum.
     */
    @Override
    public void playerFoundVisa(){
        hasVisa = true;
    }

    @Override
    public void setWinner() {
        isWinner = true;
    }

    @Override
    public boolean isWinner() {
        return isWinner;
    }

    @Override
    public boolean hasCow() {
        return hasCow;
    }

    @Override
    public boolean hasVisa() {
        return hasVisa;
    }

    /**
     * Gives the player 900kr.
     */
    @Override
    public void makeHorsePayment() {
        moneyBalance += 900;
    }

    /**
     * Robs the player of all their money.
     */
    @Override
    public void makePoopRobbery() {
        moneyBalance = 0;
    }

    /**
     * Gives the player 500kr.
     */
    @Override
    public void makePigPayment() {
        moneyBalance += 500;
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
        point.x = x;
        point.y = y;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public int getMoneyBalance() {
        return moneyBalance;
    }

    /**
     * Reduces moneyBalance with 1000 when player pays to open tile.
     */
    @Override
    public void makeTurnPayment() {
        moneyBalance -= 1000;
    }

    @Override
    public String getResourceString() {
       return PlayerColor.evaluateResourceString(PLAYER_COLOR);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void resetPlayer() {
        moneyBalance = 3000;
        point = new Point(0,0);
        hasCow = false;
        hasVisa = false;
        isWinner = false;
    }
}