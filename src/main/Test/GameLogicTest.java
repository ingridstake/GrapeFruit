import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameLogicTest {
    GameLogic gameLogic;

    @Test
    public void playerFoundCowIsWinner(){
        gameLogic = GameLogic.createGameLogic(1);
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundCow();
        GameLogic.gameLogicStartPos(players.get(0));
        assert (players.get(0).isWinner());
    }

    @Test
    public void playerFoundVisaIsWinner() {
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundVisa();
        GameLogic.gameLogicStartPos(players.get(0));
        assert (players.get(0).isWinner());
    }

    @Test
    public void openTileWithPaymentLowerMoneyBalance(){
        gameLogic = GameLogic.createGameLogic(1);
        gameLogic.movePlayer(265,85);
        gameLogic.openTileWithPayment();
        assert (gameLogic.getPlayers().get(0).getMoneyBalance() <= 2900);
    }



    @Test
    public void makeGameBoardToHighlight(){
        gameLogic = GameLogic.createGameLogic(1);
        gameLogic.getGameBoard().getPositionList().get(2).highlight();
        assert (gameLogic.getGameBoard().getPositionList().get(2).isHighlighted());
    }

}
