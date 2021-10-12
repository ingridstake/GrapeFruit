import edu.chalmers.grapefruit.Utils.IPositionable;
import edu.chalmers.grapefruit.Model.GameModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MovePlayerTest {
    private GameModel model;

    @BeforeEach
    public void testSetUp(){
        model = new GameModel();
    }

    @Test
    public void movePlayerTest() {
        List<IPositionable> positionables = model.getPositionables();
        int oldPlayerPositionX, oldPlayerPositionY, newPlayerPositionX, newPlayerPositionY, x = 0, y = 0;
        int index = 0;

        for (IPositionable positionable : positionables) {
            if (index == 2) {
                x = positionable.getX();
                y = positionable.getY();
            }

            if (!positionable.getResourceString().equals("node-view.fxml")){
                oldPlayerPositionX = positionable.getX();
                oldPlayerPositionY = positionable.getY();
                model.makePlayerMove(x, y);
                newPlayerPositionX = positionable.getX();
                newPlayerPositionY = positionable.getY();

                assert (oldPlayerPositionX != x || oldPlayerPositionY != y);
                assert (newPlayerPositionX == x && newPlayerPositionY == y);
                break;
            }
            index++;
        }
    }

    @Test
    public void dicePlayerMove(){
        List<IPositionable> positionables = model.getPositionables();
        model.makeDiceRoll();

        int oldPlayerPositionX, oldPlayerPositionY, newPlayerPositionX, newPlayerPositionY;

        for (IPositionable positionable : positionables ) {
            if (!positionable.getResourceString().equals("node-view.fxml")){
                oldPlayerPositionX = positionable.getX();
                oldPlayerPositionY = positionable.getY();
                model.makePlayerMove(300, 300);
                newPlayerPositionX = positionable.getX();
                newPlayerPositionY = positionable.getY();

                assert (oldPlayerPositionX == newPlayerPositionX || oldPlayerPositionY == newPlayerPositionY);
                assert (newPlayerPositionX !=300 && newPlayerPositionY != 300);
                break;
            }
        }
    }
}
