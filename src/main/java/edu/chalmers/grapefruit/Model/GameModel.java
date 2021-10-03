package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Interfaces.IPositionable;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

import java.util.List;

public class GameModel {
    public GameBoard gameBoard;
    List<IPlayer> players;

    public GameModel() {
        players = PlayerFactory.MakePlayers(1);
        gameBoard = new GameBoard(players);
    }

    /**
     * Returns a list of all positionable objects for the GameBoard
     * @return a list of all positionable objects of the GameBoard
     */
    public  List<IPositionable> getPositionables(){
        return gameBoard.getPositionableList();
    }
}