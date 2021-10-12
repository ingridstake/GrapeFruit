import edu.chalmers.grapefruit.Model.Json.JsonHandler;
import edu.chalmers.grapefruit.Model.Position.IPosition;
import edu.chalmers.grapefruit.Model.Position.PositionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PositionsTests {

    @Test
    public void testMakePositions(){
        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
        List<IPosition> list = PositionFactory.makePositions(handler.getJsonBoardReader().PositionList);
    }
}
