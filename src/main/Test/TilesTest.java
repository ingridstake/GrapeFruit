import edu.chalmers.grapefruit.Model.Tile.ITile;
import edu.chalmers.grapefruit.Model.Tile.TileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TilesTest {
    List<ITile> tiles;

    @BeforeEach
    public void BeforeEach(){
        tiles = TileFactory.makeTiles(15);
    }

    @Test
    public void testFactory (){
        assert (!tiles.equals(TileFactory.makeTiles(15)));
    }
}