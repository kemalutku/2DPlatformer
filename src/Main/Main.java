package Main;

import javax.swing.*;

public class Main extends JFrame {
    GamePanel panel;

    public Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Kemal Utku Lekesiz / 231111028");

        TextureManager.loadTexture("res/fire.png");
        TextureManager.loadTexture("res/ground.jpg");
        TextureManager.loadTexture("res/water.jpg");
        TextureManager.loadTexture("res/nether.png");
        TextureManager.loadTexture("res/player.png");

        this.panel = new GamePanel();
        this.add(this.panel);
        this.pack();
        this.setVisible(true);

        this.panel.startGameThread();
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}