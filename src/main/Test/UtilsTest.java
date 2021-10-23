import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import edu.chalmers.grapefruit.Utils.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilsTest {

    @Test
    public void playerCardResourceGetsRightMoneyBalance(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        PlayerCardResource playerCardResource = new PlayerCardResource(players.get(0));
        assert (playerCardResource.getMoneyBalance() == players.get(0).getMoneyBalance());
    }

    @Test
    public void playerCardResourceGetsCow(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        PlayerCardResource playerCardResource = new PlayerCardResource(players.get(0));
        players.get(0).playerFoundCow();
        assert (playerCardResource.hasCow());
    }

    @Test
    public void playerCardResourceGetsVisa(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        PlayerCardResource playerCardResource = new PlayerCardResource(players.get(0));
        players.get(0).playerFoundVisa();
        assert (playerCardResource.hasVisa());
    }

    @Test
    public void playerCardResourceGetsWinner(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        PlayerCardResource playerCardResource = new PlayerCardResource(players.get(0));
        players.get(0).setWinner();
        assert (playerCardResource.isWinner());
    }

    @Test
    public void playerCardResourceGetsString(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        PlayerCardResource playerCardResource = new PlayerCardResource(players.get(0));
        assert (playerCardResource.getResourceString() == "pink-player-card.fxml");
    }

    @Test
    public void playerCardResourceHasSamePlayer(){
        List<IPlayer> players = PlayerFactory.MakePlayers(2);
        PlayerCardResource playerCardResource1 = new PlayerCardResource(players.get(0));
        PlayerCardResource playerCardResource2 = new PlayerCardResource(players.get(1));

        assert (playerCardResource1.hasSamePlayer(players.get(0).getId()));
        assert (playerCardResource2.hasSamePlayer(players.get(1).getId()));
    }

    @Test
    public void PlayerCardResourceFactoryCreatesListWithPlayers(){
        GameModel model = new GameModel();
        model.initialize(3);
        PlayerCardResourceFactory.createPlayerCardResources(model);
        assert (PlayerCardResourceFactory.getPlayerCardResources().size() == model.getPlayers().size());
    }

    @Test
    public void viewEntityGetsRightResourceString(){
        List<IPlayer> players = PlayerFactory.MakePlayers(2);
        ViewEntity viewEntity1 = new ViewEntity(players.get(0));
        ViewEntity viewEntity2 = new ViewEntity(players.get(1));
        assert (viewEntity1.getResourceString() == "pink-player-view.fxml");
        assert (viewEntity2.getResourceString() == "purple-player-view.fxml");
    }

    @Test
    public void viewEntityGetsXAndY(){
        List<IPlayer> players = PlayerFactory.MakePlayers(1);
        ViewEntity viewEntity1 = new ViewEntity(players.get(0));
        players.get(0).updatePlayerPosition(1,2);
        assert ((viewEntity1.getX() == players.get(0).getPoint().x) &&
                (viewEntity1.getY() == players.get(0).getPoint().y));
    }

    @Test
    public void viewEntityFactoryAddsAllEntitiesToList(){
        GameModel model = new GameModel();
        model.initialize(4);
        GameLogic.resetGameLogic();
        ViewEntityFactory.createViewEntities(model);
        int nPos = model.getGameLogic().getGameBoard().getPositionList().size();
        assert (ViewEntityFactory.getViewEntities().size() == nPos + model.getPlayers().size());
    }

    @Test
    public void viewEntityFactoryClearList(){
        GameModel model = new GameModel();
        model.initialize(4);
        ViewEntityFactory.createViewEntities(model);
        ViewEntityFactory.clearViewEntityFactory();
        assert (ViewEntityFactory.getViewEntities().size() == 0);
    }
}