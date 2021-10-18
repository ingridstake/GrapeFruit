package edu.chalmers.grapefruit.Model.Player;

import java.awt.*;

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
    private Point point;

    protected Player(PlayerColor playerColor){
        moneyBalance = 5000;
        point = new Point(0,0);
        hasCow = false;
        hasVisa = false;
        PLAYER_COLOR = playerColor;
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
    public boolean hasCow() {
        return hasCow;
    }

    @Override
    public boolean hasVisa() {
        return hasVisa;
    }

    @Override
    public void makeHorsePayment() {
        moneyBalance += 1000;
    }

    @Override
    public void makePoopRobbery() {
        moneyBalance = 0;
    }

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

    public Point getPoint() {
        return point;
    }

    @Override
    public String getResourceString() throws Exception {
       return PlayerColor.evaluateResourceString(PLAYER_COLOR);
    }
} 