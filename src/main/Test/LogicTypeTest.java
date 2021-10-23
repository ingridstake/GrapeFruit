import edu.chalmers.grapefruit.Model.Position.LogicType;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LogicTypeTest {
    @Test
    public void highLightedTest() throws Exception {
        assert (LogicType.evaluatePositionResource(LogicType.HORSE,true).equals("horse-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.NONE,true).equals("node-view-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.COW,true).equals("cow-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.PIG,true).equals("pig-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.POOP,true).equals("poop-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.BLANK,true).equals("blank-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.COWBELL,true).equals("cowbell-tile-highlighted.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.START,true).equals("startPosition-highlighted-view.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.UNTURNED_TILE,true).equals("tile-view-highlighted.fxml"));

    }

    @Test
    public void notHighLightedTest() throws Exception {
        assert (LogicType.evaluatePositionResource(LogicType.HORSE,false).equals("horse-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.NONE,false).equals("node-view.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.COW,false).equals("cow-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.PIG,false).equals("pig-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.POOP,false).equals("poop-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.BLANK,false).equals("blank-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.COWBELL,false).equals("cowbell-tile.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.START,false).equals("startPosition-view.fxml"));
        assert (LogicType.evaluatePositionResource(LogicType.UNTURNED_TILE,false).equals("tile-unturned.fxml"));
    }

    @Test
    public void tileDistributionTest() {
        int nTiles = 15;
        int cowCount = 0, horseCount = 0, pigCount = 0, poopCount = 0, blankCount = 0, cowbellCount = 0;

        for (LogicType tile : LogicType.getTileTypes(nTiles)) {
            switch (tile) {
                case COW:
                    cowCount++;
                    break;
                case BLANK:
                    blankCount++;
                    break;
                case PIG:
                    pigCount++;
                    break;
                case POOP:
                    poopCount++;
                    break;
                case HORSE:
                    horseCount++;
                    break;
                case COWBELL:
                    cowbellCount++;
                    break;
            }
        }
        assert (cowbellCount + horseCount + poopCount + pigCount + blankCount + cowCount == nTiles);
        assert (cowCount == 1);
        assert (horseCount == 2);
        assert (pigCount == 3);
        assert (poopCount == 3);
        assert (cowbellCount == 4);
        assert (blankCount == 2);
    }
}
