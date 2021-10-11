import edu.chalmers.grapefruit.Model.Json.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonTest {

    @Test
    public void testReadJsonFile() {
        JsonHandler handler = new JsonHandler("edu/chalmers/grapefruit/Model/board.json");

        if (handler == null)
            assertTrue(false);

    }
}