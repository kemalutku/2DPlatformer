package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import static Main.Settings.*;


public class TextureManager {

    public static BufferedImage loadTexture(String path) {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(new File(path));
            Image resized_gt = texture.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_DEFAULT);
            texture = new BufferedImage(TILE_SIZE, TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
            texture.getGraphics().drawImage(resized_gt, 0, 0, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

    public static BufferedImage loadTexture(String path, Double scale) {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(new File(path));
            Image resized_gt = texture.getScaledInstance((int) (TILE_SIZE * scale), (int) (TILE_SIZE * scale), Image.SCALE_DEFAULT);
            texture = new BufferedImage((int) (TILE_SIZE * scale), (int) (TILE_SIZE * scale), BufferedImage.TYPE_INT_ARGB);
            texture.getGraphics().drawImage(resized_gt, 0, 0, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

}
