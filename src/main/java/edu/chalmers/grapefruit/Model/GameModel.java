package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.CurrentPlayer;
import edu.chalmers.grapefruit.Utils.*;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

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
        players = PlayerFactory.MakePlayers(n);
        if (players == null) {
            throw new IllegalArgumentException("More than 4 players is not allowed");
        }
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

    public CurrentPlayer getCurrentPlayer() {
        return gameLogic.getCurrentPlayer();
    }


    public List<IPlayer> getPlayers(){
        return players;
    }

    public GameLogic getGameLogic(){
        return gameLogic;
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