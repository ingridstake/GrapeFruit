package edu.chalmers.grapefruit.Model.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileFactory {

    static List<ITile> makeTiles(int nTiles){
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

        if (tiles.size() == nTiles) {
            Collections.shuffle(tiles);
            return tiles;
        }

        return null;
    }
}
