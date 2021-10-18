import edu.chalmers.grapefruit.Model.GameModel;
import edu.chalmers.grapefruit.Model.ViewEntity;
import edu.chalmers.grapefruit.Model.ViewEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MovePlayerTest {
    private GameModel model;
    private List<ViewEntity> viewEntities;

    @BeforeEach
    public void testSetUp(){
        model = new GameModel();
        ViewEntityFactory.clearViewEntityFactory();
        viewEntities = ViewEntityFactory.getViewEntities();
    }

    @Test
    public void movePlayerTest() {
        int oldPlayerPositionX, oldPlayerPositionY, newPlayerPositionX, newPlayerPositionY, x = 0, y = 0;
        int index = 0;

        for (ViewEntity positionable : viewEntities) {
            if (index == 2) {
                x = positionable.getX();
                y = positionable.getY();
            }

            if (positionable.getResourceString().equals("pink-player-view.fxml")){
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
        model.makeDiceRoll();

        int oldPlayerPositionX, oldPlayerPositionY, newPlayerPositionX, newPlayerPositionY;

        for (ViewEntity viewEntity : viewEntities ) {
            if (!viewEntity.getResourceString().equals("node-view.fxml")){
                oldPlayerPositionX = viewEntity.getX();
                oldPlayerPositionY = viewEntity.getY();
                model.makePlayerMove(300, 300);
                newPlayerPositionX = viewEntity.getX();
                newPlayerPositionY = viewEntity.getY();

                assert (oldPlayerPositionX == newPlayerPositionX || oldPlayerPositionY == newPlayerPositionY);
                assert (newPlayerPositionX !=300 && newPlayerPositionY != 300);
                break;
            }
        }
    }
}
