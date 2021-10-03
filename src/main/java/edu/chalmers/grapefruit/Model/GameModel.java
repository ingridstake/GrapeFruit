package edu.chalmers.grapefruit.Model;

import edu.chalmers.grapefruit.Interfaces.IPositionable;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public GameBoard gameBoard;
    List<IPlayer> players;


    public GameModel() {
        players = PlayerFactory.MakePlayers(1);
        gameBoard = new GameBoard(players);


    }

    public  List<IPositionable> getPositionables(){
        List<IPositionable> positionables = new ArrayList<>();

        for (Node node : gameBoard.getMap().getAllNodes() ) {
            positionables.add(node.getPosition());
        }

        return positionables;
    }

    public void init() {

    }
}
