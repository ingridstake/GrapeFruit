import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerColor;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import org.junit.jupiter.api.Test;

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
    public void playerGetsRightResourceString(){
        List<IPlayer> players = PlayerFactory.MakePlayers(4);
        assert (players.get(0).getResourceString() == PlayerColor.evaluateResourceString(PlayerColor.PINK));
        assert (players.get(3).getResourceString() == PlayerColor.evaluateResourceString(PlayerColor.YELLOW));
    }
    @Test
    public void updatePlayerPositionTest(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        players.get(0).updatePlayerPosition(1,2);
        assert (players.get(0).getX() == 1 && players.get(0).getY() == 2);
    }
}