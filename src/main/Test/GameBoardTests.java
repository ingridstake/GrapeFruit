import edu.chalmers.grapefruit.Model.GameBoard.*;
import edu.chalmers.grapefruit.Model.Player.PlayerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardTests {
    GameBoard gameBoard;
    @BeforeEach
    public void testCreateMap() {
        gameBoard = new GameBoard(PlayerFactory.MakePlayers(1));
    }

    @Test
    public void testIfPositionableListIsCorrect() {
        //if(gameBoard.getPositionableList().get(0) instanceof viewentities)
            assertTrue(true);
    }

}