package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.LogicType;
import edu.chalmers.grapefruit.Model.Position.TilePosition;
import edu.chalmers.grapefruit.Utils.Listeners.NewTurnListener;
import edu.chalmers.grapefruit.Utils.Listeners.OpenTileListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tovenilsson
 * @author olimanstrom
 */

public class GameLogic {

    private List<NewTurnListener> newTurnListeners = new ArrayList<>();
    private List<OpenTileListener> openTileListeners = new ArrayList<>();
    private boolean canRollDiceToOpenTile = false;
    private boolean canPayToOpenTile = false;

    /**
     * Creates a new instance of gameLogic ig there is not already one and returns it.
     * @param nPlayers is the number of players for the game.
     * @return the instance of the class, if there is already one this is the instance that is returned.
     */
    public static GameLogic createGameLogic(int nPlayers){
        if (instance == null){
            instance = new GameLogic(nPlayers);
        }
        return instance;
    }
    private static GameLogic instance = null;

    private List<IPlayer> players;
    private IPlayer currentPlayer;
    private List<IPlayer> tileTurnIsOngoingForPlayer = new ArrayList<>();
    private boolean cowIsFound = false;
    private GameBoard gameBoard;

    private GameLogic(int nPlayers){
        this.players = PlayerFactory.MakePlayers(nPlayers);
        this.gameBoard = new GameBoard(players);
        currentPlayer = players.get(0);
    }

    //TODO Ta bort? - inte ta bort
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
        if (currentPlayer.getMoneyBalance() >= 1000){
            currentPlayer.makeTurnPayment();
            executeTileTurn(currentPlayer, gameBoard.getNode(currentPlayer));
        }
    }

    /**
     * Turns the current tile if the dice's value is grater than 4. Else the turn is passed on to the next player.
     * @return
     */
    public boolean openTileWithDice(){
        Dice dice = new Dice(6);
        dice.roll();
        if (dice.getValue() >= 4 ){
            executeTileTurn(currentPlayer, gameBoard.getNode(currentPlayer));
            return true;
        } else {
            setNextCurrentPlayer();
        }
        return false;
    }

    /**
     * Adds the current player to the list of players who are allowed to turn a tile.
     */
    private void beginTurnTileForPlayer(){
        if (currentPlayer.getMoneyBalance() >= 1000){
            notifyOpenTileListeners(true, true);
        }
        else {
            notifyOpenTileListeners(true, false);
        }
        tileTurnIsOngoingForPlayer.add(currentPlayer);
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
        if (tileTurnIsOngoingForPlayer.contains(currentPlayer)){
            tileTurnIsOngoingForPlayer.remove(currentPlayer);
        }
        Node newNode = gameBoard.movePlayer(x,y, currentPlayer);
        executeGameLogic(currentPlayer, newNode);
    }

    /**
     * Calls the gameBoard rollDice.
     */
    public void makeDiceRoll() {
        gameBoard.makeDiceRoll(currentPlayer);
    }

    /**
     * Iterates through the list of players to find and set the next player to current player.
     */
    private void setNextCurrentPlayer() {
        notifyOpenTileListeners(false, false);
        boolean referencePlayerIsFound = false;
        for (int i = 1; i <= players.size(); i++) {
            if (referencePlayerIsFound) {
                System.out.println(i);
                currentPlayer = players.get(i-1);
                notifyNewTurn();
                return;
            }

            if (players.get(i-1) == currentPlayer) {
                referencePlayerIsFound = true;
            }
            if (i >= players.size()) {
                i = 0;
            }
        }
    }

    public void addTurnListener(NewTurnListener newTurnListener) {
        newTurnListeners.add(newTurnListener);
    }

    public void addOpenTileListener(OpenTileListener openTileListener) {
        openTileListeners.add(openTileListener);
    }

    private void notifyNewTurn() {
        for (NewTurnListener newTurnListener : newTurnListeners) {
            newTurnListener.newTurn(currentPlayer.getId());
        }
    }

    private void notifyOpenTileListeners(boolean canRollDiceToOpenTile, boolean canPayToOpenTile) {
        for (OpenTileListener listener : openTileListeners) {
            listener.updateDiceToOpenTile(canRollDiceToOpenTile);
            listener.updatePayToOpenTile(canPayToOpenTile);
        }
    }

    /**
     * Returns the game board.
     * @return the game board.
     */
    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public List<Integer> getPlayerIds() {
        List<Integer> ids = new ArrayList<>();
        for(IPlayer player : players){
            ids.add(player.getId());
        }
        return ids;
    }

    public List<IPlayer> getPlayers() {
        return players;
    }
}