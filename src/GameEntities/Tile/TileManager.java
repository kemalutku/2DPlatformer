package GameEntities.Tile;

import LevelBuilder.LevelTiles;
import Main.GamePanel;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import static Main.Settings.*;
import static Main.TextureManager.loadTexture;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int[][] tileLocations;
    public int[][] entityLocations;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[8];

        tiles[0] = new Tile(loadTexture("res/ground.jpg"));
        tiles[1] = new Tile(loadTexture("res/fire.png"));
        tiles[2] = new Tile(loadTexture("res/water.jpg"));
        tiles[3] = new Tile(loadTexture("res/nether.png"));
        tiles[4] = new Tile(loadTexture("res/coin.png"));
        tiles[5] = new Tile(loadTexture("res/door.png")); // monster
        tiles[6] = new Tile(loadTexture("res/door.png"));
        tiles[7] = new Tile(loadTexture("res/spawner.png"));


        tileLocations = new int[MAX_SCREEN_COL][MAX_SCREEN_ROW];
        entityLocations = new int[MAX_SCREEN_COL][MAX_SCREEN_ROW];
        loadLevel();
    }

    private void loadLevel() {
        LevelTiles lvlTiles = null;

        try {
            FileInputStream fileIn = new FileInputStream("Level.obj");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            lvlTiles = (LevelTiles) in.readObject();
            in.close();
        } catch (FileNotFoundException ignored) {

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int[] entityLocation : entityLocations) {
            Arrays.fill(entityLocation, -1);
        }

        assert lvlTiles != null;
        tileLocations = lvlTiles.getTileCoordinates();
        for (int i = 0; i < tileLocations.length; i++) {
            for (int j = 0; j < tileLocations[i].length; j++) {
                int tileType = tileLocations[i][j];
                if (tileType > 3) {
                    tileLocations[i][j] = -1;
                    entityLocations[i][j] = tileType;
                }
            }
        }

        tileLocations[3][1] = 7; //Monster spawner
        tileLocations[0][8] = 6; //Game End Door
        tileLocations[0][7] = 6;

    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < MAX_SCREEN_COL; i++) {
            for (int j = 0; j < MAX_SCREEN_ROW; j++) {
                int tileType = tileLocations[i][j];
                if (tileType != -1) {
                    g2.drawImage(tiles[tileType].image, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }
}
