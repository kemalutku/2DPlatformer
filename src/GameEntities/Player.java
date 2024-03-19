package GameEntities;

import GameEntities.Attack.Attack;
import GameEntities.Attack.FireAttack;
import GameEntities.Attack.WaterAttack;
import GameEntities.Elemental.*;
import Main.KeyHandler.KeyHandler;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Main.Settings.*;

public class Player extends GameEntity {
    public ElementalState fire, water, space;
    public BufferedImage lastSprite;

    GamePanel gp;

    long currentTime = 0;
    long lastTime = 0;

    boolean attackReady = false;
    KeyHandler keyH;

    public boolean isPlayer2 = false;

    public Player(int x, int y, GamePanel gp, KeyHandler keyH) {
        super(x, y, TILE_SIZE, TILE_SIZE);
        this.gp = gp;
        this.keyH = keyH;
        fire = new FireState();
        water = new WaterState();
        space = new SpaceState();
        elementalState = fire;
        lastSprite = elementalState.left1;
    }

    public void setPlayer2(boolean isPlayer2) {
        this.isPlayer2 = isPlayer2;
    }

    public void update() {
        if (!isPlayer2) {
            if (keyH.attackPressed1) {
                attackReady = true;
            } else {
                if (attackReady) {
                    Attack newAttack;
                    if (elementalState instanceof FireState) {
                        newAttack = new FireAttack(this.x, this.y, this.direction, this.gp, 1);
                        gp.addPlayerAttack(newAttack);
                    } else if (elementalState instanceof WaterState) {
                        newAttack = new WaterAttack(this.x, this.y, this.direction, this.gp, 1);
                        gp.addPlayerAttack(newAttack);
                    }
                    attackReady = false;
                }
            }
            if (keyH.firePressed1) {
                elementalState = fire;
            }
            if (keyH.waterPressed1) {
                elementalState = water;
            }
            if (keyH.spacePressed1) {
                elementalState = space;
            }
            if (keyH.upPressed1) {
                if (onFloor) {
                    vy -= GRAVITY * speed;
                    onFloor = false;
                    if (elementalState instanceof SpaceState) {
                        vy *= 1.25F;
                    }
                }
            }
            if (keyH.downPressed1) {
                this.vy = speed;
            }
            if (keyH.leftPressed1) {
                direction = "left";
                this.vx = -speed;
            }
            if (keyH.rightPressed1) {
                direction = "right";
                this.vx = speed;
            }
        } else {
            if (keyH.attackPressed2) {
                attackReady = true;
            } else {
                if (attackReady) {
                    Attack newAttack;
                    if (elementalState instanceof FireState) {
                        newAttack = new FireAttack(this.x, this.y, this.direction, this.gp, 2);
                        gp.addPlayerAttack(newAttack);
                    } else if (elementalState instanceof WaterState) {
                        newAttack = new WaterAttack(this.x, this.y, this.direction, this.gp, 2);
                        gp.addPlayerAttack(newAttack);
                    }
                    attackReady = false;
                }
            }
            if (keyH.firePressed2) {
                elementalState = fire;
            }
            if (keyH.waterPressed2) {
                elementalState = water;
            }
            if (keyH.spacePressed2) {
                elementalState = space;
            }
            if (keyH.upPressed2) {
                if (onFloor) {
                    vy -= GRAVITY * speed;
                    onFloor = false;
                    if (elementalState instanceof SpaceState) {
                        vy *= 1.25F;
                    }
                }
            }
            if (keyH.downPressed2) {
                this.vy = speed;
            }
            if (keyH.leftPressed2) {
                direction = "left";
                this.vx = -speed;
            }
            if (keyH.rightPressed2) {
                direction = "right";
                this.vx = speed;
            }
        }


        if (!onFloor) {
            currentTime = System.nanoTime();
            double delta = (double) (currentTime - lastTime) / 1000000000;
            if (!(delta > 5)) {
                vy += (float) (speed * delta * 100);
                if (vy > speed) {
                    vy = speed;
                }
            }
        }

        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkEntity(this);

        if (!collisionOnHorizon) {
            x += (int) vx;
        }
        if (!collisionOnVertical) {
            y += (int) vy;
            onFloor = false;
        }

        this.vx = 0;

        spriteCounter++;
        if (spriteCounter > 30) {
            if (spriteNum == 1) {
                spriteNum = 2;
                spriteCounter = 0;
            } else if (spriteNum == 2) {
                spriteNum = 1;
                spriteCounter = 0;
            }
        }

        lastTime = currentTime;
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case "left":
                if (spriteNum == 1) {
                    lastSprite = elementalState.left1;
                } else if (spriteNum == 2) {
                    lastSprite = elementalState.left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    lastSprite = elementalState.right1;
                } else if (spriteNum == 2) {
                    lastSprite = elementalState.right2;
                }
                break;
        }
        g.drawImage(lastSprite, this.x, this.y, null);
    }
}