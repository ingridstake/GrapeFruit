package edu.chalmers.grapefruit.Model.Player;

import edu.chalmers.grapefruit.Utils.ViewEntityResource;

import java.awt.*;

/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public interface IPlayer extends ViewEntityResource {

    //TODO: detta kanske borde göras till flera mindre interfaces, kan hända att det orsakar problem med catsning osv. men vora snyggt!
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

    void resetPlayer();
}