import edu.chalmers.grapefruit.Model.Json.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class JsonTests {

    @Test
    public void testCreateJsonHandler(){
        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
    }

    @Test
    public void testReadWrongJsonFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            JsonHandler handler = new JsonHandler("notAFilePath");
        });
    }

    @Test
    public void testCreateJsonMap(){
        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");
        JsonBoardReader map = handler.getJsonBoardReader();
    }

    @Test
    public void testCreateJsonHandlerWithWrongWrongStructure(){
        assertThrows(IllegalArgumentException.class, () -> {
            JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/ForTesting/incorrectBoard.json");
        });
    }
}