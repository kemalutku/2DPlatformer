package GameEntities;

import GameEntities.Elemental.ElementalState;

import java.awt.*;

public abstract class GameEntity extends Rectangle {
    public String direction = "right";
    public int speed = 4;
    public boolean collisionOnHorizon, collisionOnVertical;

    public float vx = 0;
    public float vy = 0;
    public boolean onFloor = false;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int health = 3;

    public ElementalState elementalState;


    public GameEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
    }

    public Rectangle getCopy() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public abstract void draw(Graphics g);

    public abstract void update();

    public String getElementalName() {
        if (this.elementalState != null) {
            return this.elementalState.getElementName();
        } else {
            return "";
        }
    }
}

