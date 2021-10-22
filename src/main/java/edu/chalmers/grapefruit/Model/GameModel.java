package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Utils.Listeners.DiceRolledListener;
import edu.chalmers.grapefruit.Utils.Listeners.OpenTileListener;
import edu.chalmers.grapefruit.Utils.Observable;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Utils.*;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import edu.chalmers.grapefruit.Utils.Listeners.NewTurnListener;

import java.util.ArrayList;
import java.util.List;
/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class GameModel implements Observable {

    private GameLogic gameLogic;
    private List<IPlayer> players;
    private List<Observer> observerList = new ArrayList<>();

    /**
     * Creates n players and a game board depending on the created players.
     * @param n is the amount of players.
     */
    public void initialize(int n){
        if (n<1 || n>4){
            throw new IllegalArgumentException("More than 4 players is not allowed");
        }
        players = PlayerFactory.MakePlayers(n);
        gameLogic = GameLogic.createGameLogic(players);
    }

    public void makePlayerMove(int x, int y){
        gameLogic.movePlayer(x, y);
        notifyObservers();
    }

    public void makeDiceRoll(){
        gameLogic.makeDiceRoll();
        notifyObservers();
    }

    public void payToOpen(){
        gameLogic.openTileWithPayment();
        notifyObservers();
    }

    public void diceToOpen(){
        gameLogic.openTileWithDice();
        notifyObservers();
    }

    public List<Integer> getPlayerIds(){
        List<Integer> ids = new ArrayList<Integer>();
        for(IPlayer player : players){
            ids.add(player.getId());
        }
        return ids;
    }

    /**
     * Returns a list of all positionable objects for the GameBoard
     * @return a list of all positionable objects of the GameBoard
     */
    public List<ViewEntity> getViewEntities() {
        return ViewEntityFactory.getViewEntities();
    }

    public List<IPlayer> getPlayers() {
        return players;
    }

    public GameLogic getGameLogic(){
        return gameLogic;
    }

    public void addNewTurnListener(NewTurnListener listener) {
        gameLogic.addTurnListener(listener);
    }

    public void addOpenTileListener(OpenTileListener listener) {
        gameLogic.addOpenTileListener(listener);
    }

    public void addDiceRolledListener(DiceRolledListener listener) {
        gameLogic.addDiceListener(listener);
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}