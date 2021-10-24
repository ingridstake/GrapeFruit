package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Utils.Listeners.DiceRolledListener;
import edu.chalmers.grapefruit.Utils.Listeners.OpenTileOperationsListener;
import edu.chalmers.grapefruit.Utils.Listeners.WinnerFoundListener;
import edu.chalmers.grapefruit.Utils.Observable;
import edu.chalmers.grapefruit.Utils.Observer;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Utils.Listeners.NewTurnListener;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Ingrid Stake
 * @author Tove Nilsson
 * @author Elvina Fahlgren
 * @author Olivia Månström
 */
public class GameModel implements Observable {

    private GameLogic gameLogic;
    private List<Observer> observerList = new ArrayList<>();

    /**
     * Creates n players and a game board depending on the created players.
     * @param n is the amount of players.
     * @throws IllegalArgumentException if n isn't in the range of [1,4].
     */
    public void initialize(int n){
        if (n<1 || n>4){
            throw new IllegalArgumentException("More than 4 players is not allowed");
        }
        gameLogic = GameLogic.createGameLogic(n);
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

    public boolean diceToOpen(){
        boolean turnCouldBeDone = gameLogic.openTileWithDice();
        notifyObservers();
        return turnCouldBeDone;
    }

    public List<Integer> getPlayerIds(){
        return gameLogic.getPlayerIds();
    }

    public List<IPlayer> getPlayers() {
        return gameLogic.getPlayers();
    }

    public GameLogic getGameLogic(){
        return gameLogic;
    }

    public void addNewTurnListener(NewTurnListener listener) {
        gameLogic.addTurnListener(listener);
    }

    public void addOpenTileListener(OpenTileOperationsListener listener) {
        gameLogic.addOpenTileListener(listener);
    }

    public void addDiceRolledListener(DiceRolledListener listener) {
        gameLogic.addDiceListener(listener);
    }

    public void addWinnerFoundListener(WinnerFoundListener listener) {
        gameLogic.addWinnerFoundListener(listener);
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