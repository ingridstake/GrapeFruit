import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import edu.chalmers.grapefruit.Model.Position.LogicType;
import edu.chalmers.grapefruit.Model.Position.Position;
import edu.chalmers.grapefruit.Model.Position.TilePosition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameLogicTest {

    @Test
    public void playerFoundCowIsWinner(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundCow();
        GameLogic.gameLogicStartPos(players.get(0));
        assert (players.get(0).isWinner());
    }

    @Test
    public void playerFoundVisaIsWinner(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundVisa();
        GameLogic.gameLogicStartPos(players.get(0));
        assert (players.get(0).isWinner());
    }

    @Test
    public void logicTypeHorseGivesPlayerMoney(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        TilePosition tilePosition = new TilePosition(1, 1);
        tilePosition.setLogicType(LogicType.HORSE);
        Node node = new Node(tilePosition);
        GameLogic.gameLogicPlayerAction(players.get(0), node);
        GameLogic.executeGameLogic(players.get(0), node);

        assert (players.get(0).getMoneyBalance() == 6000);
    }

    @Test
    public void playerFoundVisaAndWonTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(2);

        TilePosition tilePosition1 = new TilePosition(1, 1);
        tilePosition1.setLogicType(LogicType.COW);

        TilePosition tilePosition2 = new TilePosition(2, 2);
        tilePosition2.setLogicType(LogicType.COWBELL);

        Node node1 = new Node(tilePosition1);
        Node node2 = new Node(tilePosition2);

        GameLogic.gameLogicPlayerAction(players.get(0), node1);
        GameLogic.executeGameLogic(players.get(0), node1);

        GameLogic.gameLogicPlayerAction(players.get(1), node2);
        GameLogic.executeGameLogic(players.get(1), node2);

        GameLogic.gameLogicStartPos(players.get(1));

        assert (players.get(0).hasCow());
        assert (players.get(1).hasVisa());
        assert (players.get(1).isWinner());
    }

}
