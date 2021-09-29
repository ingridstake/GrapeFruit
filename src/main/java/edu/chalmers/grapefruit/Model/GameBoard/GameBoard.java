package edu.chalmers.grapefruit.Model.GameBoard;

import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * @author ingrid.stake
 * @author tove.nilsson
 * @author elvina.fahlgren
 * @author olivia.månström
 */
public class GameBoard {

    HashMap<IPlayer, Node> playerPositionHashMap;
    IPlayer currPlayer;
    Map map;

    public GameBoard(List<IPlayer> players){
        playerPositionHashMap = new HashMap<>();
        map = MapFactory.createMap(8);

        for (IPlayer player : players) {
            playerPositionHashMap.put(player, map.getStartNode());
        }
    }

    public void movePlayer(Node newNode){
        playerPositionHashMap.replace(currPlayer, newNode);
    }

    public Map getMap() {
        return map;
    }

    /*
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
     */
}

