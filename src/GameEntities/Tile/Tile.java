package GameEntities.Tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = true;

    Tile(BufferedImage image) {
        this.image = image;
    }
}
