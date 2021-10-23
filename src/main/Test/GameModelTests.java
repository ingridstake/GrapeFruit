import edu.chalmers.grapefruit.Model.GameLogic;
import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.Player.IPlayer;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.LogicType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;

public class GameModelTests {
    GameModel gameModel = new GameModel();
    //List<Boolean> assertList = new ArrayList<>();

    @Before
    public void before(){

        GameLogic.resetGameLogic();
    }

    @Test
    public void makePlayerMoveTest(){
        gameModel.initialize(1);
        GameLogic.resetGameLogic();
        gameModel.makePlayerMove(170, 100);
        assert (gameModel.getPlayers().get(0).getPoint().getX() == 170) && (gameModel.getPlayers().get(0).getPoint().getY() == 100);
    }

    @Test
    public void payToOpenTest3(){
        gameModel.initialize(1);
        gameModel.makePlayerMove(170, 100);
        gameModel.makePlayerMove(225, 130);
        gameModel.makePlayerMove(265, 85);
        gameModel.payToOpen();
        assert gameModel.getGameLogic().getPlayers().get(0).getMoneyBalance() <= 2900;
    }

    @Test
    public void diceToOpenTest2(){
        gameModel.initialize(1);
        GameLogic.resetGameLogic();
        gameModel.makePlayerMove(170, 100);
        gameModel.makePlayerMove(225, 130);
        gameModel.makePlayerMove(265, 85);
        List<IPosition> positions = gameModel.getGameLogic().getGameBoard().getPositionList();
        int oldUnturnedCount = 0, newUnturnedCount = 0;

        for (IPosition position : positions) {
            if (position.getLogicType() == LogicType.UNTURNED_TILE) {
                oldUnturnedCount++;
            }
        }
        while (true){
            gameModel.makePlayerMove(225, 130);
            gameModel.makePlayerMove(265, 85);
            if (gameModel.diceToOpen()){
                break;
            }
        }
        for (IPosition position : positions) {
            if (position.getLogicType() == LogicType.UNTURNED_TILE) {
                newUnturnedCount++;
            }
        }

        assert (oldUnturnedCount > newUnturnedCount);
    }

    @Test
    public void playerIdGetTest(){
        gameModel.initialize(1);
        List<Integer> playerId = gameModel.getPlayerIds();
        for (IPlayer player : gameModel.getPlayers()) {
            assert (playerId.contains(player.getId()));
        }
        assert (playerId.size() == gameModel.getPlayers().size());
    }

    @Test
    public void initializeThrowsException(){
        assertThrows (IllegalArgumentException.class, () -> { gameModel.initialize(5);} );
    }

   /*
    @Test
    public void listenerTest(){
        gameModel.initialize(1);
        GameLogic.resetGameLogic();

        DiceRolledListener  diceRolledListener = new DiceRolledListener() {
            //public boolean updated = false;
            @Override
            public void updateDiceValue(int diceValue) {
                assert true;
                assertList.add(new Boolean(true));
            }
        };
        OpenTileOperationsListener openTileOperationsListener = new OpenTileOperationsListener() {
            @Override
            public void updateDiceToOpenTile(boolean canRollDiceToOpenTile) {
                assertList.add(new Boolean(true));
            }

            @Override
            public void updatePayToOpenTile(boolean canPayToOpenTile) {
                assertList.add(new Boolean(true));
            }
        };
        NewTurnListener newTurnListener = new NewTurnListener() {
            @Override
            public void newTurn(int currentPlayerId) {
                assertList.add(new Boolean(true));
            }
        };
        gameModel.addDiceRolledListener(diceRolledListener);
        gameModel.addNewTurnListener(newTurnListener);
        gameModel.addOpenTileListener(openTileOperationsListener);

        gameModel.makePlayerMove(170, 100);
        gameModel.makePlayerMove(225, 130);
        gameModel.makePlayerMove(265, 85);

        gameModel.diceToOpen();

        assert (assertList.size()==4);
    }
    */
}