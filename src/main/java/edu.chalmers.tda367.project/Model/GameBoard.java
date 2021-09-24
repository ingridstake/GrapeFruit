package main.java.edu.chalmers.tda367.project.Model;

import main.java.edu.chalmers.tda367.project.Model.Player.IPlayer;
import main.java.edu.chalmers.tda367.project.Model.Player.PlayerFactory;
import main.java.edu.chalmers.tda367.project.Model.Position.IPosition;
import main.java.edu.chalmers.tda367.project.Model.Position.PositionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameBoard {

    HashMap<IPosition, List<IPosition>> positionsHashMap;
    HashMap<IPlayer, IPosition> playerPositionHashMap;
    IPlayer currPlayer;

    public GameBoard(int nPlayers){
        playerPositionHashMap = new HashMap<>();
        positionsHashMap = PositionFactory.makePositions();
        List<IPlayer> playerList = PlayerFactory.MakePlayers(nPlayers);
        currPlayer = playerList.get(0);
        for (IPlayer player: playerList) {
            playerPositionHashMap.put(player, PositionFactory.startPosition);
        }

    }

    public void movePlayer(IPosition newPosition){
        playerPositionHashMap.replace(currPlayer, newPosition);
    }

    public void run (){
        Scanner sc=new Scanner(System.in);

        System.out.print("X O O \nO   O\nO O O");


        while (true){
            System.out.print("\n \n Ange positionen du vill gå till (0-7): ");
            int ans = sc.nextInt();

            for (Map.Entry<IPosition, List<IPosition>> positionEntry : positionsHashMap.entrySet()){
                if (positionEntry.getKey().getId() == ans){
                    movePlayer(positionEntry.getKey());
                    break;
                }
            }

            String gameString = getGameString();
            System.out.print(gameString);
        }
    }

    private String getGameString(){
        char x = 'X';
        char o = 'O';
        StringBuilder string = new StringBuilder();

        int id = playerPositionHashMap.get(currPlayer).getId();

        for (int i = 0; i<8; i++){
            if (i == 3 || i == 5)
                string.append('\n');
            if (i==id)
                string.append('X');
            else
                string.append('O');

            if (i == 3)
                string.append(' ');
        }

        return string.toString();
    }
}
