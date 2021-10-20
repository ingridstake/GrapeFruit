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
 * @author olimanstrom
 */

public class GameLogic {

    /**
     * Creates a new instance of gameLogic ig there is not already one and returns it.
     * @param players is the list of players for the game.
     * @return the instance of the class, if there is already one this is the instance that is returned.
     */
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
     * Determines what game logic should be executed for the current node and player.
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    private void executeGameLogic(IPlayer currentPlayer, Node newNode) {
        IPosition position = newNode.getPosition();
        if (position.getLogicType() == LogicType.UNTURNED_TILE) {
            if (tileTurnIsOngoingForPlayer.contains(currentPlayer)){
                    executeTileTurn(currentPlayer, newNode);
            } else {
                beginTurnTileForPlayer();
            }
        } else if (newNode.getPosition().getLogicType() == LogicType.START){
            gameLogicStartPos(currentPlayer);
        } else {
            setNextCurrentPlayer();
        }
    }

    /**
     * Turns the current tile by charging the player.
     */
    public void openTileWithPayment(){
        IPlayer player = currentPlayer.getCurrentPlayer();
        if (player.getMoneyBalance() >= 1000){
            player.makeTurnPayment();
            executeTileTurn(player, gameBoard.getNode(player));
        }
    }

    /**
     * Turns the current tile if the dice's value is grater than 4. Else the turn is passed on to the next player.
     */
    public void openTileWithDice(){
        IPlayer player = currentPlayer.getCurrentPlayer();
        Dice dice = new Dice(6);
        dice.roll();
        if (dice.getValue() >= 4 ){
            executeTileTurn(player, gameBoard.getNode(player));
        } else {
            setNextCurrentPlayer();
        }
    }

    /**
     * Adds the current player to the list of players who are allowed to turn a tile.
     */
    private void beginTurnTileForPlayer(){
        tileTurnIsOngoingForPlayer.add(currentPlayer.getCurrentPlayer());
    }

    /**
     * Turns the tile and based on the turned tiles LogicType the player receives different actions.
     * @param currentPlayer is the current player.
     * @param newNode is the new position of the player.
     */
    private void executeTileTurn(IPlayer currentPlayer, Node newNode){
        IPosition position = newNode.getPosition();
        TilePosition tilePosition = position instanceof TilePosition ? (TilePosition) position : null;

        tilePosition.turnTile();

        switch (tilePosition.getLogicType()){
            case COW:
                cowIsFound = true;
                currentPlayer.playerFoundCow();
                break;
            case COWBELL:
                if (cowIsFound) {
                    currentPlayer.playerFoundVisa();
                }
                break;
            case PIG:
                currentPlayer.makePigPayment();
                break;
            case HORSE:
                currentPlayer.makeHorsePayment();
                break;
            case POOP:
                currentPlayer.makePoopRobbery();
                break;
        }
        tileTurnIsOngoingForPlayer.remove(currentPlayer);
        setNextCurrentPlayer();
    }

    /**
     * Determines if a player that reaches the start position is a valid winner of the game.
     * @param currentPlayer is the player in question.
     */
    public static void gameLogicStartPos(IPlayer currentPlayer){
        if (currentPlayer.hasCow() || currentPlayer.hasVisa()){
            currentPlayer.setWinner();
        }
    }

    /**
     * Calls the gameBoard to update the players position.
     * @param x is the new x coordinate.
     * @param y is the new y coordinate.
     */
    public void movePlayer(int x, int y) {
        IPlayer player = currentPlayer.getCurrentPlayer();
        if (tileTurnIsOngoingForPlayer.contains(player)){
            tileTurnIsOngoingForPlayer.remove(player);
        }
        Node newNode = gameBoard.movePlayer(x,y, player);
        executeGameLogic(player, newNode);
    }

    /**
     * Calls the gameBoard rollDice.
     */
    public void makeDiceRoll() {
        gameBoard.makeDiceRoll(currentPlayer.getCurrentPlayer());
    }

    /**
     * Returns the current player.
     * @return the current player.
     */
    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Iterates through the list of players to find and set the next player to current player.
     */
    private void setNextCurrentPlayer() {
        boolean referencePlayerIsFound = false;

        for (int i = 1; i <= players.size(); i++) {
            if (referencePlayerIsFound) {
                currentPlayer.setNewCurrentPlayer(players.get(i-1));
                return;
            }

            if (players.get(i-1) == currentPlayer.getCurrentPlayer()) {
                referencePlayerIsFound = true;
            }
            if (i >= players.size()) {
                i = 0;
            }
        }
    }
}