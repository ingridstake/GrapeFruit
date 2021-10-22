import edu.chalmers.grapefruit.Model.Dice;
import edu.chalmers.grapefruit.Model.GameBoard.GameBoard;
import edu.chalmers.grapefruit.Model.GameBoard.Node;
import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import edu.chalmers.grapefruit.Model.Position.IPosition;
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
    public void openTileWithPaymentLowerMoneyBalance(){

        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        GameLogic gameLogic = GameLogic.createGameLogic(players);
        gameLogic.movePlayer(265,85);
        gameLogic.openTileWithPayment();
        assert (players.get(0).getMoneyBalance() <= 2900);
    }

    @Test
    public void makeGameBoardToHighlight(){
        List<IPlayer> players = PlayerFactory.MakePlayers(2);
        GameLogic gameLogic = GameLogic.createGameLogic(players);
        gameLogic.getGameBoard().getPositionList().get(2).highlight();
        assert (gameLogic.getGameBoard().getPositionList().get(2).isHighlighted());
    }
}