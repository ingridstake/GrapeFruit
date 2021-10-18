package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.LogicType;
import edu.chalmers.grapefruit.Model.Position.TilePosition;

/**
 * @author tovenilsson
 */

public class GameLogic {

    /**
     * The gameLogic for "Den försvunna kossan".
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */

    public static void executeGameLogic(IPlayer currentPlayer, Node newNode) {
        IPosition position = newNode.getPosition();
        if (position.getLogicType() == LogicType.UNTURNED_TILE) {
            TilePosition tilePosition = position instanceof TilePosition ? (TilePosition) position : null;
            if (tilePosition != null){
                gameLogicPlayer(currentPlayer, newNode);
            }
        }
    }

    /**
     * Turns the tile and based on the turned tiles LogicType the player receives different actions.
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */

    public static void gameLogicPlayer(IPlayer currentPlayer, Node newNode){
        IPosition position = newNode.getPosition();
        TilePosition tilePosition = position instanceof TilePosition ? (TilePosition) position : null;

        tilePosition.turnTile();

        switch (tilePosition.getLogicType()){
            case COW:
                currentPlayer.playerFoundCow();
                return;
            case COWBELL:
                currentPlayer.playerFoundVisa();
                return;
            case PIG:
                currentPlayer.makePigPayment();
                return;
            case HORSE:
                currentPlayer.makeHorsePayment();
                return;
            case POOP:
                currentPlayer.makePoopRobbery();
        }
    }
}