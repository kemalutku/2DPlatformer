package GameEntities;

import GameEntities.Attack.Attack;
import GameEntities.Monster.Monster;
import GameEntities.World.Coin;
import Main.GamePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static Main.Settings.*;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(GameEntity entity) {
        try {
            Rectangle nextX = entity.getCopy();
            nextX.setSize(entity.width - 2, entity.height - 2);
            nextX.setLocation((int) (nextX.x + entity.vx), nextX.y);
            Rectangle nextY = entity.getCopy();
            nextY.setLocation(entity.x, (int) (nextY.y + entity.vy));
            nextY.setSize(entity.width - 2, entity.height - 2);

            Map<Integer, String> elementalMap = new HashMap<>();
            elementalMap.put(0, "ground");
            elementalMap.put(1, "fire");
            elementalMap.put(2, "water");
            elementalMap.put(3, "space");

            entity.collisionOnVertical = false;
            entity.collisionOnHorizon = false;

            entity.collisionOnHorizon = (nextX.x < 0) || (nextX.x + nextX.width > TILE_SIZE * MAX_SCREEN_COL);
            entity.collisionOnVertical = (nextY.y < 0) || (nextY.y + nextY.height > TILE_SIZE * MAX_SCREEN_ROW);

            int nextXRightCol = (nextX.x + nextX.width - 1) / TILE_SIZE;
            int nextXLeftCol = nextX.x / TILE_SIZE;
            int nextXTopRow = nextX.y / TILE_SIZE;
            int nextXBottomRow = (nextX.y + nextX.height - 1) / TILE_SIZE;

            int tileType1 = gp.tileManager.tileLocations[nextXRightCol][nextXTopRow];
            int tileType2 = gp.tileManager.tileLocations[nextXRightCol][nextXBottomRow];
            int tileType3 = gp.tileManager.tileLocations[nextXLeftCol][nextXTopRow];
            int tileType4 = gp.tileManager.tileLocations[nextXLeftCol][nextXBottomRow];

            if (entity.vx > 0) { // Moving right
                if (tileType1 != -1 || tileType2 != -1) {
                    entity.collisionOnHorizon = true;
                }
            } else if (entity.vx < 0) { // Moving left
                if (tileType3 != -1 || tileType4 != -1) {
                    entity.collisionOnHorizon = true;
                }
            }
            if (entity instanceof Player) {
                if (entity.collisionOnHorizon) {
                    if (tileType1 > 0) {
                        if (tileType1 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType1), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType2 > 0) {
                        if (tileType2 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType2), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType3 > 0) {
                        if (tileType3 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType3), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType4 > 0) {
                        if (tileType4 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType4), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                }
            } else if (entity instanceof Attack) {
                if (entity.collisionOnHorizon) {
                    gp.removeEntityRequest(entity);
                    System.out.println("remove attack");
                }
            }
            int nextYBotRow = (nextY.y + nextY.height - 1) / TILE_SIZE;
            int nextYTopRow = nextY.y / TILE_SIZE;
            int nextYLeftCol = nextY.x / TILE_SIZE;
            int nextYRightCol = (nextY.x + nextY.width - 1) / TILE_SIZE;

            int tileType5 = gp.tileManager.tileLocations[nextYLeftCol][nextYBotRow];
            int tileType6 = gp.tileManager.tileLocations[nextYRightCol][nextYBotRow];
            int tileType7 = gp.tileManager.tileLocations[nextYLeftCol][nextYTopRow];
            int tileType8 = gp.tileManager.tileLocations[nextYRightCol][nextYTopRow];

            if (entity.vy > 0 && (tileType5 != -1 || tileType6 != -1)) {
                Rectangle leftYrect1 = new Rectangle(nextYLeftCol * TILE_SIZE, nextYBotRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                Rectangle leftYrect2 = new Rectangle(nextYRightCol * TILE_SIZE, nextYBotRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                entity.collisionOnVertical = nextY.intersects(leftYrect1) || nextY.intersects(leftYrect2);
                entity.onFloor = true;
            } else if (entity.vy < 0 && (tileType7 != -1 || tileType8 != -1)) {
                Rectangle leftYrect1 = new Rectangle(nextYLeftCol * TILE_SIZE, nextYTopRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                Rectangle leftYrect2 = new Rectangle(nextYRightCol * TILE_SIZE, nextYTopRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                entity.collisionOnVertical = nextY.intersects(leftYrect1) || nextY.intersects(leftYrect2);
            }
            if (entity instanceof Player) {
                if (entity.collisionOnVertical) {
                    if (tileType5 > 0) {
                        if (tileType5 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType5), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType6 > 0) {
                        if (tileType6 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType6), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType7 > 0) {
                        if (tileType7 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType7), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                    if (tileType8 > 0) {
                        if (tileType8 == 6) {
                            gp.endGame();
                        }
                        if (!Objects.equals(elementalMap.get(tileType8), entity.elementalState.elementName)) {
                            gp.resetGameRequest();
                        }
                    }
                }
            } else if (entity instanceof Attack) {
                if (entity.collisionOnHorizon) {
                    gp.removeEntityRequest(entity);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            gp.resetGameRequest();
        }

    }

    public void checkEntity(GameEntity gameEntity) {
        if (gameEntity instanceof Player) {
            int playerNo = ((Player) gameEntity).isPlayer2 ? 2:1;
            for (GameEntity entity : gp.gameEntityArrayList) {
                if (Objects.equals(gameEntity, entity)) {
                    continue;
                }
                if (entity instanceof Monster) {
                    if (entity.intersects(gameEntity)) {
                        gp.resetGameRequest();
                    }
                } else if (entity instanceof Coin) {
                    if (entity.intersects(gameEntity)) {
                        gp.removeEntityRequest(entity);
                        gp.addScore(5);
                    }
                } else if (entity instanceof Attack) {
                    if (entity.intersects(gameEntity)) {
                        Attack ao = (Attack) entity;
                        if (ao.owner != playerNo) {
                            gameEntity.health -= 1;
                            gp.removeEntityRequest(entity);
                            if (gameEntity.health == 0) {
                                if (((Player) gameEntity).isPlayer2) {
                                    gp.endGame(1);
                                } else {
                                    gp.endGame(2);
                                }

                            }
                        }
                    }
                }
            }
        }
        if (gameEntity instanceof Monster) {
            for (GameEntity entity : gp.gameEntityArrayList) {
                if (Objects.equals(gameEntity, entity)) {
                    continue;
                }
                if (entity instanceof Attack) {
                    if (entity.intersects(gameEntity)) {
                        boolean monster_dead = ((Monster) gameEntity).takeHit((Attack) entity);
                        if (monster_dead) {
                            gp.removeEntityRequest(gameEntity);
                            gp.addScore(10);
                        }
                    }

                }
            }


        }
    }
}

