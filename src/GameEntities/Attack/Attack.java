package GameEntities.Attack;

import GameEntities.Elemental.ElementalState;
import GameEntities.GameEntity;
import Main.GamePanel;
import Main.TextureManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Settings.*;

public abstract class Attack extends GameEntity {
    BufferedImage attackImage;
    public ElementalState elementalState;
    GamePanel gp;
    public int owner;

    public Attack(int x, int y, String direction, ElementalState elementalState, GamePanel gp, String a_dir, int owner) {
        super(x, y, (int) (TILE_SIZE * ATTACK_SIZE), (int) (TILE_SIZE * ATTACK_SIZE));
        this.speed *= 2;
        this.direction = direction;
        this.elementalState = elementalState;
        this.attackImage = TextureManager.loadTexture(a_dir, ATTACK_SIZE);
        this.gp = gp;
        this.owner = owner;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.attackImage, (int) (this.x + TILE_SIZE * ATTACK_SIZE), (int) (this.y + TILE_SIZE * ATTACK_SIZE), null);
    }

    @Override
    public void update() {
        gp.collisionChecker.checkTile(this);

        switch (direction) {
            case "right":
                this.x += speed;
                break;
            case "left":
                this.x -= speed;
                break;
        }
    }
}
