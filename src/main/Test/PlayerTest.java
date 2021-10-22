import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerColor;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class PlayerTest {

    @Test
    public void playerHasCowTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundCow();
        assert (players.get(0).hasCow());
    }
    @Test
    public void playerHasVisaTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).playerFoundVisa();
        assert (players.get(0).hasVisa());
    }
    @Test
    public void playerGetsRightColor(){
        List<IPlayer> players = PlayerFactory.MakePlayers(4);
        assert (players.get(0).getPlayerColor() == PlayerColor.PINK);
        assert (players.get(1).getPlayerColor() == PlayerColor.PURPLE);
        assert (players.get(2).getPlayerColor() == PlayerColor.TURQUOISE);
        assert (players.get(3).getPlayerColor() == PlayerColor.YELLOW);
    }

    @Test
    public void updatePlayerPositionTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).updatePlayerPosition(1,2);
        assert (players.get(0).getPoint().equals(new Point(1,2)));
    }

    @Test
    public void playerGotMoneyTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        assert (players.get(0).getMoneyBalance() == 3000);
    }

    @Test
    public void playerGotHorseTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).makeHorsePayment();
        assert (players.get(0).getMoneyBalance() == 3900);
    }

    @Test
    public void playerGotPoopTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).makePoopRobbery();
        assert (players.get(0).getMoneyBalance() == 0);
    }

    @Test
    public void playerGotPigTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).makePigPayment();
        assert (players.get(0).getMoneyBalance() == 3500);
    }

    @Test
    public void playerColorFxmlFilesTest() throws Exception {
        List<IPlayer> players = PlayerFactory.MakePlayers(4);
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < players.size(); j++){
                if (i != j ) {
                    assert (players.get(i).getResourceString() != players.get(j).getResourceString());
                }
            }
        }
    }
}