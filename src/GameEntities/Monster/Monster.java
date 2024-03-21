package GameEntities.Monster;

import GameEntities.Attack.Attack;
import GameEntities.Elemental.ElementalState;
import GameEntities.Elemental.GoldState;
import GameEntities.GameEntity;
import Main.GamePanel;

import java.awt.*;
import java.util.Objects;

import static Main.Settings.TILE_SIZE;

public abstract class Monster extends GameEntity {
    GamePanel gp;

    public Monster(int x, int y, GamePanel gp, ElementalState elementalState) {
        super(x, y, TILE_SIZE, TILE_SIZE);
        this.gp = gp;
        this.elementalState = elementalState;
    }

    public boolean takeHit(Attack a) {
        if (!(elementalState instanceof GoldState)) {
            if (a.elementalState.getClass() == this.elementalState.getClass()) {
                this.health += 1;
            } else {
                this.health -= 1;
            }
        }
        gp.removeEntityRequest(a);
        return this.health == 0;
    }

    @Override
    public void update() {
        if (Objects.equals(direction, "right")) {
            this.vx = speed;
            this.vy = speed;
        } else {
            this.vx = -speed;
        }
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkEntity(this);

        if (!collisionOnHorizon) {
            x += (int) vx;
        } else {
            if (Objects.equals(direction, "right")) {
                direction = "left";
            } else {
                direction = "right";
            }

        }
        if (!collisionOnVertical) {
            y += (int) vy;
        }
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case "left":
                g.drawImage(this.elementalState.getLeft1(), this.x, this.y, null);
                break;
            case "right":
                g.drawImage(this.elementalState.getRight1(), this.x, this.y, null);
                break;
        }
    }
}
