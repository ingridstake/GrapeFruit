import edu.chalmers.grapefruit.Model.GameLogic;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class GameLogicTest {
    GameLogic gameLogic;

    @Test
    public void playerFoundCowIsWinner(){
        gameLogic = GameLogic.createGameLogic(1);
        GameLogic.resetGameLogic();
        gameLogic.movePlayer(170, 100);
        gameLogic.getPlayers().get(0).playerFoundCow();
        gameLogic.gameLogicStartPos(gameLogic.getPlayers().get(0));
        assert (gameLogic.getPlayers().get(0).isWinner());
    }

    @Test
    public void playerFoundVisaIsWinner() {
        gameLogic = GameLogic.createGameLogic(1);
        GameLogic.resetGameLogic();
        gameLogic.getPlayers().get(0).playerFoundVisa();
        gameLogic.gameLogicStartPos(gameLogic.getPlayers().get(0));
        assert (gameLogic.getPlayers().get(0).isWinner());
    }


    @Test
    public void openTileWithPaymentLowerMoneyBalance(){
        gameLogic = GameLogic.createGameLogic(1);
        gameLogic.movePlayer(170, 100);
        gameLogic.movePlayer(225, 130);
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

    @Test
    public void gameLogicResetTest(){
        gameLogic = GameLogic.createGameLogic(1);
        GameLogic.resetGameLogic();
        gameLogic.movePlayer(170, 100);
        gameLogic.movePlayer(225, 130);
        gameLogic.movePlayer(265,85);
        gameLogic.openTileWithPayment();
        assert (gameLogic.getPlayers().get(0).getMoneyBalance() <= 2900);
        assert (gameLogic.getPlayers().get(0).getPoint().equals(new Point(265,85)));
        GameLogic.resetGameLogic();
        assert (gameLogic.getPlayers().get(0).getMoneyBalance() == 3000);
        assert (gameLogic.getPlayers().get(0).getPoint().equals(new Point(45,30)));

    }
}