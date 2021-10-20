package edu.chalmers.grapefruit.Model.Player;

import edu.chalmers.grapefruit.Utils.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private boolean isWinner;

    protected Player(PlayerColor playerColor){
        moneyBalance = 5000;
        point = new Point(0,0);
        hasCow = false;
        hasVisa = false;
        isWinner = false;
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
     * Gives the player 1000kr.
     */
    @Override
    public void makeHorsePayment() {
        moneyBalance += 1000;
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

    public Point getPoint() {
        return point;
    }

    @Override
    public int getMoneyBalance() {
        return moneyBalance;
    }

    @Override
    public String getResourceString() throws Exception {
       return PlayerColor.evaluateResourceString(PLAYER_COLOR);
    }
}