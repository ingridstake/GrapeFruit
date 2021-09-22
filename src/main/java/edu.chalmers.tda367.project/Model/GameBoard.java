package main.java.edu.chalmers.tda367.project.Model;

import main.java.edu.chalmers.tda367.project.Model.Player.IPlayer;
import main.java.edu.chalmers.tda367.project.Model.Player.PlayerFactory;
import main.java.edu.chalmers.tda367.project.Model.Position.IPosition;
import main.java.edu.chalmers.tda367.project.Model.Position.PositionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    HashMap<Integer, List<IPosition>> positionsHashMap;
    HashMap<IPlayer, IPosition> playerPositionHashMap;

    public GameBoard(int nPlayers){
        playerPositionHashMap = new HashMap<>();
        positionsHashMap = PositionFactory.makePositions();
        List<IPlayer> playerList = PlayerFactory.MakePlayers(nPlayers);
        for (IPlayer player: playerList) {
            playerPositionHashMap.put(player, PositionFactory.startPosition);
        }

    }

    public void movePlayer(IPlayer currentPlayer, IPosition newPosition){

        playerPositionHashMap.replace(currentPlayer, newPosition);
    }
}
