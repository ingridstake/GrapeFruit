package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.Player;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

import java.util.List;

public class GameModel {
    public GameBoard gameBoard;
    List<IPlayer> players;

    public GameModel() {
        init();

    }

    public void init() {
        players = PlayerFactory.MakePlayers(1);
        gameBoard = new GameBoard(players);
    }
}
