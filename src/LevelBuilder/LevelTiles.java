package LevelBuilder;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class LevelTiles implements Serializable {
    @Serial
    private static final long serialVersionUID = 7289169032704376985L;
    private final int[][] tileCoordinates = new int[64][40];

    LevelTiles() {
        for (int[] tileCoordinate : tileCoordinates) {
            Arrays.fill(tileCoordinate, -1);
        }
    }

    public void addTile(int tileCoordinateX, int tileCoordinateY, int tileType) {
        try {
            this.tileCoordinates[tileCoordinateX][tileCoordinateY] = tileType;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    public void addTile(int tileCoordinateX, int tileCoordinateY, Color tileColor) {
        int tileType = 0;
        if (tileColor == Color.RED) {
            tileType = 1;
        } else if (tileColor == Color.BLUE) {
            tileType = 2;
        } else if (tileColor == Color.MAGENTA) {
            tileType = 3;
        }
        else if (tileColor == Color.WHITE) {
            tileType = -1;
        }
        else if (tileColor == Color.ORANGE) {
            tileType = 4;
        }
        else if (tileColor == Color.GREEN) {
            tileType = 5;
        }
        else if (tileColor == Color.CYAN) {
            tileType = 6;
        }
        addTile(tileCoordinateX, tileCoordinateY, tileType);
    }

    public int[][] getTileCoordinates() {
        return tileCoordinates;
    }

    public void setTiles(int[][] tiles) {

    }
}
