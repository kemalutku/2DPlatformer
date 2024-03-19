package Main;

import GameEntities.Attack.Attack;
import GameEntities.World.Coin;
import GameEntities.CollisionChecker;
import GameEntities.GameEntity;
import GameEntities.KeyHandler.KeyHandler;
import GameEntities.Monster.FireMonster;
import GameEntities.Monster.GoldMonster;
import GameEntities.Monster.Monster;
import GameEntities.Monster.WaterMonster;
import GameEntities.Player;
import GameEntities.Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import static Main.Settings.*;

public class GamePanel extends JPanel implements Runnable {

    public final int screenWidth = TILE_SIZE * MAX_SCREEN_COL;
    public final int screenHeight = TILE_SIZE * MAX_SCREEN_ROW;
    public int score = 0;
    private boolean pause = false;

    public ArrayList<GameEntity> gameEntityArrayList = new ArrayList<>();

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    private final Stack<Attack> playerAttackCreationBuffer = new Stack<>();
    private final Stack<GameEntity> entityRemovalBuffer = new Stack<>();
    public boolean resetGameReq = false;
    public boolean versusMode = false;

    Random random = new Random();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(173, 216, 230));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        Player player1 = new Player(50, 900, this, this.keyHandler);
        gameEntityArrayList.add(player1);

        for (int i = 0; i < tileManager.entityLocations.length; i++) {
            for (int j = 0; j < tileManager.entityLocations[i].length; j++) {
                int entityType = tileManager.entityLocations[i][j];
                switch (entityType) {
                    case 4:
                        gameEntityArrayList.add(new Coin(i * TILE_SIZE, j * TILE_SIZE));
                        break;
                    case 5:
                        generateMonster(i, j);
                        break;
                }
            }
        }
    }

    public void generateMonster(int i, int j) {
        int flipResult = 1 + random.nextInt(3);
        Monster nextMonster = switch (flipResult) {
            case 1 -> new FireMonster(i * TILE_SIZE, j * TILE_SIZE, this);
            case 2 -> new WaterMonster(i * TILE_SIZE, j * TILE_SIZE, this);
            case 3 -> new GoldMonster(i * TILE_SIZE, j * TILE_SIZE, this);
            default -> null;
        };
        gameEntityArrayList.add(nextMonster);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double spawnInterval = (double) 1000000000 / 0.1;
        double deltaDraw = 0;
        double deltaSpawn = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            deltaDraw += (currentTime - lastTime) / drawInterval;
            deltaSpawn += (currentTime - lastTime) / spawnInterval;
            lastTime = currentTime;
            if (deltaDraw >= 1) {
                if (this.pause == this.keyHandler.pauseTyped) {
                    update();
                    repaint();
                }
                deltaDraw--;
            }
            if (deltaSpawn >= 1) {
                spawnMonster();
                deltaSpawn--;
            }

        }
    }

    public void update() {
        if (keyHandler.savePressed) {
            saveGame();
        }
        if (keyHandler.loadPressed) {
            loadGame();
        }
        if (keyHandler.versusTyped != versusMode) {
            resetGame(keyHandler.versusTyped);
            keyHandler.versusTyped = false;
        }
        while (!playerAttackCreationBuffer.isEmpty()) {
            gameEntityArrayList.add(playerAttackCreationBuffer.pop());
        }
        while (!entityRemovalBuffer.isEmpty()) {
            gameEntityArrayList.remove(entityRemovalBuffer.pop());
        }
        for (GameEntity ent : this.gameEntityArrayList) {
            ent.update();
        }
        if (resetGameReq) {
            resetGame();
            resetGameReq = false;
        }
    }

    public void addPlayerAttack(Attack a) {
        playerAttackCreationBuffer.push(a);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (GameEntity ent : this.gameEntityArrayList) {
            ent.draw(g2);
        }
        drawScore(g2);

        tileManager.draw(g2);
        g2.dispose();
    }

    public void resetGame() {
        Player player = new Player(50, 900, this, this.keyHandler);
        gameEntityArrayList.clear();
        gameEntityArrayList.add(player);
        for (int i = 0; i < tileManager.entityLocations.length; i++) {
            for (int j = 0; j < tileManager.entityLocations[i].length; j++) {
                int entityType = tileManager.entityLocations[i][j];
                switch (entityType) {
                    case 4:
                        gameEntityArrayList.add(new Coin(i * TILE_SIZE, j * TILE_SIZE));
                        break;
                    case 5:
                        generateMonster(i, j);
                        break;
                }
            }
        }
        score = 0;
        playerAttackCreationBuffer.clear();
        System.out.println("Game reset");
    }

    public void resetGame(boolean versusMode) {
        if (versusMode) {

            gameEntityArrayList.clear();
            playerAttackCreationBuffer.clear();
            score = 0;
            Player player1 = new Player(50, 900, this, this.keyHandler);
            Player player2 = new Player(50, 50, this, this.keyHandler);
            player2.setPlayer2(true);
            gameEntityArrayList.add(player1);
            gameEntityArrayList.add(player2);
        } else {
            resetGame();
        }
    }

    public void drawScore(Graphics2D g) {
        g.drawString(String.valueOf(this.score), 25, 25);
    }

    public void resetGameRequest() {
        resetGameReq = true;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    public void removeEntityRequest(GameEntity entity) {
        entityRemovalBuffer.add(entity);
    }

    public void spawnMonster() {
        generateMonster(3, 1);
    }

    public void endGame() {
        JOptionPane.showMessageDialog(this, "Score: " + score, "Popup Title", JOptionPane.INFORMATION_MESSAGE);
        resetGameRequest();
    }

    public void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("SaveFile.obj");
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            Map<String, Object> state = new HashMap<>();
            state.put("score", this.score);
            ArrayList<Map<String, Object>> entities = new ArrayList<>();

            for (GameEntity entity : gameEntityArrayList) {
                Map<String, Object> entityData = new HashMap<>();
                entityData.put("x", entity.getX());
                entityData.put("y", entity.getY());
                entityData.put("health", entity.health);
                entityData.put("element", entity.getElementalName());
                entityData.put("class", entity.getClass());
                entities.add(entityData);
            }

            state.put("entities", entities);
            ois.writeObject(state);
            System.out.println("Saved");
        } catch (IOException ignored) {
            System.out.println("Save Failed.");
        }
    }


    public void loadGame() {
        try {
            gameEntityArrayList.clear();
            FileInputStream fis = new FileInputStream("SaveFile.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, Object> state = (Map<String, Object>) ois.readObject();
            score = (Integer) state.get("score");
            ArrayList<Map<String, Object>> entData = (ArrayList<Map<String, Object>>) state.get("entities");
            for (Map<String, Object> ent : entData) {
                Class clazz = (Class) ent.get("class");
                String className = clazz.getName();
                double x = (double) ent.get("x");
                double y = (double) ent.get("y");
                int health = (int) ent.get("health");
                String element = (String) ent.get("element");

                if (className.contains("Player")) {
                    Player loadedPlayer = new Player((int) x, (int) y, this, keyHandler);
                    loadedPlayer.health = health;
                    switch (element) {
                        case "fire":
                            loadedPlayer.elementalState = loadedPlayer.fire;
                            loadedPlayer.lastSprite = loadedPlayer.elementalState.left1;
                            break;
                        case "water":
                            loadedPlayer.elementalState = loadedPlayer.water;
                            break;
                        case "space":
                            loadedPlayer.elementalState = loadedPlayer.space;
                            break;
                    }
                    gameEntityArrayList.add(loadedPlayer);
                } else if (className.contains("FireMonster")) {
                    FireMonster m = new FireMonster((int) x, (int) y, this);
                    gameEntityArrayList.add(m);
                } else if (className.contains("WaterMonster")) {
                    WaterMonster m = new WaterMonster((int) x, (int) y, this);
                    gameEntityArrayList.add(m);
                } else if (className.contains("GoldMonster")) {
                    GoldMonster m = new GoldMonster((int) x, (int) y, this);
                    gameEntityArrayList.add(m);
                }  else if (className.contains("Coin")) {
                    Coin c = new Coin((int) x, (int) y);
                    gameEntityArrayList.add(c);
                }
            }

        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


