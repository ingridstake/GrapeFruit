import edu.chalmers.grapefruit.Model.Json.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class JsonTest {

    @Test
    public void testCreateJsonHandler(){
        new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
    }

    @Test
    public void testReadWrongJsonFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            new JsonHandler("notAFilePath");
        });
    }

    @Test
    public void testCreateJsonMap(){
        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
        handler.getJsonBoardReader();
    }

    @Test
    public void testCreateJsonHandlerWithWrongStructure(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JsonHandler("edu/chalmers/grapefruit/ForTesting/incorrectBoard.json");
        });
    }

    @Test
    public void testCreateJsonHandlerWithDifferentListSizes(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JsonHandler("edu/chalmers/grapefruit/ForTesting/incorrectBoardDiffSizeList.json");
        });
    }
}