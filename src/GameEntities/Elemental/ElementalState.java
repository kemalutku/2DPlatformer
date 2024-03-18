package GameEntities.Elemental;

import Main.TextureManager;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class ElementalState{
    public transient BufferedImage left1, left2, right1, right2;
    public String elementName;

    public ElementalState(String l1_dir, String l2_dir) {
        this.left1 = TextureManager.loadTexture(l1_dir);
        this.left2 = TextureManager.loadTexture(l2_dir);

        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance(-this.left1.getHeight(), 0));
        this.right1 = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(this.left1, null);
        this.right2 = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter(this.left2, null);
    }
}
