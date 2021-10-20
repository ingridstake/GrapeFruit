package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.LogicType;
import edu.chalmers.grapefruit.Model.Position.TilePosition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tovenilsson
 */

public class GameLogic {

    private static List<IPlayer> tileTurnIsOngoingForPlayer = new ArrayList<>();
    private static boolean cowIsFound = false;

    //TODO ändra currentplayer när turen faktiskt är slut

    /**
     * The gameLogic for "Den försvunna kossan".
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    public static void executeGameLogic(IPlayer currentPlayer, Node newNode) {
        IPosition position = newNode.getPosition();
        if (position.getLogicType() == LogicType.UNTURNED_TILE) {
            if (tileTurnIsOngoingForPlayer.contains(currentPlayer)){
                    gameLogicPlayerAction(currentPlayer, newNode);
            } else {
                beginPlayerMove(currentPlayer);
            }
        } else if (newNode.getPosition().getLogicType() == LogicType.START){
            gameLogicStartPos(currentPlayer);
        }
    }

    public static void executeGameLogicWithPayment(IPlayer currentPlayer, Node newNode){
        if (currentPlayer.getMoneyBalance() >= 1000){
            currentPlayer.makeTurnPayment();
            gameLogicPlayerAction(currentPlayer, newNode);
        }
    }

    public static void executeGameLogicWithDice(IPlayer currentPlayer, Node newNode){
        Dice dice = new Dice(6);
        dice.roll();
        if (dice.getValue() >= 4 ){
            gameLogicPlayerAction(currentPlayer, newNode);
        }
    }

    private static void beginPlayerMove(IPlayer currentPlayer){
        tileTurnIsOngoingForPlayer.add(currentPlayer);
    }

    /**
     * Turns the tile and based on the turned tiles LogicType the player receives different actions.
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    public static void gameLogicPlayerAction(IPlayer currentPlayer, Node newNode){
        IPosition position = newNode.getPosition();
        TilePosition tilePosition = position instanceof TilePosition ? (TilePosition) position : null;

        tilePosition.turnTile();

        switch (tilePosition.getLogicType()){
            case COW:
                cowIsFound = true;
                currentPlayer.playerFoundCow();
                return;
            case COWBELL:
                if (cowIsFound) {
                    currentPlayer.playerFoundVisa();
                }
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
        tileTurnIsOngoingForPlayer.remove(currentPlayer);
    }

    public static void gameLogicStartPos(IPlayer currentPlayer){
        if (currentPlayer.hasCow() || currentPlayer.hasVisa()){
            currentPlayer.setWinner();
        }
    }
}