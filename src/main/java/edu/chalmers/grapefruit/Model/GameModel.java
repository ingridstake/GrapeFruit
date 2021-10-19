package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.CurrentPlayer;
import edu.chalmers.grapefruit.Utils.Observable;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
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
    private GameBoard gameBoard;
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
        gameBoard = new GameBoard(players);
    }

    public void makePlayerMove(int x, int y){
        gameBoard.movePlayer(x, y);
        notifyObservers();
    }

    public void makeDiceRoll(){
        gameBoard.makeDiceRoll();
        notifyObservers();
    }

    public CurrentPlayer getCurrentPlayer() {
        return gameBoard.getCurrentPlayer();
    }

    /**
     * Returns a list of all positionable objects for the GameBoard
     * @return a list of all positionable objects of the GameBoard
     */
    public List<ViewEntity> getViewEntities(){
        return ViewEntityFactory.getViewEntities();
    }

    public List<PlayerCardResource> getPlayerCardResources() { return PlayerCardResourceFactory.getPlayerCardResources(); }

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