package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Utils.IPositionable;
import edu.chalmers.grapefruit.Utils.Observable;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Observable {
    public GameBoard gameBoard;
    List<IPlayer> players;
    List<Observer> observerList = new ArrayList<>();

    public GameModel() {
        players = PlayerFactory.MakePlayers(1);
        if (players == null) {
            throw new IllegalArgumentException("More than 4 players is not allowed");
        }
        else {
            gameBoard = new GameBoard(players);
        }
    }

    public void makePlayerMove(int x, int y){
        gameBoard.movePlayer(x, y);
        notifyObservers();
    }

    public void makeDiceRoll(){
        gameBoard.makeDiceRoll();
        notifyObservers();
    }

    /**
     * Returns a list of all positionable objects for the GameBoard
     * @return a list of all positionable objects of the GameBoard
     */
    public  List<IPositionable> getPositionables(){
        return gameBoard.getPositionableList();
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println(observerList);
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}