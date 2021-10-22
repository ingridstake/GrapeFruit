package edu.chalmers.grapefruit.Model.Player;

import edu.chalmers.grapefruit.Utils.Observable;
import edu.chalmers.grapefruit.Utils.ViewEntityResource;

import java.awt.*;

/**
     * @author ingrid.stake
     * @author tove.nilsson
     * @author elvina.fahlgren
     * @author olivia.månström
     */
public interface IPlayer extends ViewEntityResource {
    /**
     * Returns the color of the player.
     * @return the color of the player.
     */
    PlayerColor getPlayerColor();

    /**
     * Updates the position of the player.
     * @param x is the x-value of the position.
     * @param y is the y-value of the position.
     */
    void updatePlayerPosition(int x, int y);

    /**
     * Sets hasCow to true if player found the cow.
     */
    void playerFoundCow();
    /**
     * Sets hasVisum to true if player found a visum.
     */
    void playerFoundVisa();

    void setWinner();

    boolean isWinner();

    boolean hasCow();

    boolean hasVisa();


    void makePigPayment();

    void makeHorsePayment();

    void makePoopRobbery();

    Point getPoint();

    int getMoneyBalance();

    void makeTurnPayment();

    int getId();
}