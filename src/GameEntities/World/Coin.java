package GameEntities.World;

import GameEntities.GameEntity;
import Main.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Settings.TILE_SIZE;

public class Coin extends GameEntity {
    public static BufferedImage sprite = TextureManager.loadTexture("res/coin.png", 0.75);

    public Coin(int x, int y) {
        super(x, y, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, this.x, this.y, null);
    }

    @Override
    public void update() {

    }
}
