package edu.chalmers.grapefruit.Model.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ingrid.stake
 */

public class TileFactory {

    /**
     * Creates a list of tiles with the right tile type distribution.
     * @param nTiles is the number of tiles the returned list is to contain, this should not be lower than 15.
     * @return a list of Tiles in shuffled order.
     */
    public static List<ITile> makeTiles(int nTiles) {
        List<ITile> tiles = new ArrayList<>();

        tiles.add(new Tile(TileType.COW));

        for (int i = 0; i < nTiles * 2 / 15; i++) {
            tiles.add(new Tile(TileType.HORSE));
        }

        for (int i = 0; i < nTiles / 5; i++) {
            tiles.add(new Tile(TileType.PIG));
        }

        for (int i = 0; i < 4 * nTiles / 15; i++) {
            tiles.add(new Tile(TileType.COWBELL));
        }

        for (int i = 0; i < nTiles / 5; i++) {
            tiles.add(new Tile(TileType.POOP));
        }

        while (tiles.size()<nTiles) {
            tiles.add(new Tile(TileType.BLANK));
        }

        Collections.shuffle(tiles);
        return tiles;
    }
}