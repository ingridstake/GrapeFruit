package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.CurrentPlayer;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
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

    public static GameLogic createGameLogic(List<IPlayer> players){
        if (instance == null){
            instance = new GameLogic(players);
        }
        return instance;
    }
    private static GameLogic instance = null;

    private List<IPlayer> players;
    private CurrentPlayer currentPlayer;
    private List<IPlayer> tileTurnIsOngoingForPlayer = new ArrayList<>();
    private boolean cowIsFound = false;
    private GameBoard gameBoard;

    private GameLogic(List<IPlayer> players){
        this.players = players;
        this.gameBoard = new GameBoard(players);
        currentPlayer = new CurrentPlayer(players.get(0));
    }

    private GameLogic(){ }

    /**
     * The gameLogic for "Den försvunna kossan".
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    private void executeGameLogic(IPlayer currentPlayer, Node newNode) {
        IPosition position = newNode.getPosition();
        if (position.getLogicType() == LogicType.UNTURNED_TILE) {
            if (tileTurnIsOngoingForPlayer.contains(currentPlayer)){
                    gameLogicPlayerAction(currentPlayer, newNode);
            } else {
                beginPlayerMove();
            }
        } else if (newNode.getPosition().getLogicType() == LogicType.START){
            gameLogicStartPos(currentPlayer);
        }
    }

    public void openTileWithPayment(){
        IPlayer player = currentPlayer.getCurrentPlayer();
        if (player.getMoneyBalance() >= 1000){
            player.makeTurnPayment();
            gameLogicPlayerAction(player, gameBoard.getNode(player));
        }
    }

    public void openTileWithDice(){
        IPlayer player = currentPlayer.getCurrentPlayer();
        Dice dice = new Dice(6);
        dice.roll();
        if (dice.getValue() >= 4 ){
            gameLogicPlayerAction(player, gameBoard.getNode(player));
        }
    }

    private void beginPlayerMove(){
        tileTurnIsOngoingForPlayer.add(currentPlayer.getCurrentPlayer());
    }

    /**
     * Turns the tile and based on the turned tiles LogicType the player receives different actions.
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    private void gameLogicPlayerAction(IPlayer currentPlayer, Node newNode){
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

    public void movePlayer(int x, int y) {
        Node newNode = gameBoard.movePlayer(x,y, currentPlayer.getCurrentPlayer());
        executeGameLogic(currentPlayer.getCurrentPlayer(), newNode);
        //currentPlayer.setNewCurrentPlayer(getNextPlayer(currentPlayer.getCurrentPlayer()));
    }

    public void makeDiceRoll() {
        gameBoard.makeDiceRoll(currentPlayer.getCurrentPlayer());
    }

    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Iterates through the list of players to find the next player in the list from the referencePlayer.
     * @param referencePlayer is the player the next player is evaluated from.
     * @return the IPlayer that is next in the playerList.
     */
    private IPlayer getNextPlayer(IPlayer referencePlayer) {
        boolean referencePlayerIsFound = false;

        for (int i = 1; i <= players.size(); i++) {
            if (referencePlayerIsFound) {
                return players.get(i-1);
            }

            if (players.get(i-1) == referencePlayer) {
                referencePlayerIsFound = true;
            }
            if (i >= players.size()) {
                i = 0;
            }
        }

        return referencePlayer;
    }
}