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
     * Checks if the tile is unturned, if it is the tile is turned.
     * Based on the LogicType of the turned tile the current player gets different properties.
     * @param currentPlayer is the current player
     * @param newNode is the new position of the player
     */

    public static void executeGameLogic(IPlayer currentPlayer, Node newNode) {
        IPosition position = newNode.getPosition();
        if (position.getLogicType() == LogicType.UNTURNED_TILE) {
            TilePosition tilePosition = position instanceof TilePosition ? (TilePosition) position : null;
            if (tilePosition != null){
                //TODO flytta ut till en egen metod
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
    }

    private void gameLogicPlayer(){

    }
}