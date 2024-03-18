package LevelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static Main.Settings.*;

public class LevelBuilder extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    private final JPanel drawPanel;
    private int hoverX = -1, hoverY = -1;
    private Graphics offScreenGraphics;
    private Image offScreenImage;
    public Color paintColor = Color.BLACK;
    public LevelTiles tiles = new LevelTiles();
    int SQUARE_SIZE = TILE_SIZE;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 32;
    public final int screenWidth = SQUARE_SIZE * maxScreenCol;
    public final int screenHeight = SQUARE_SIZE * maxScreenRow;


    LevelBuilder() {
        super("Level Builder");

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (offScreenImage == null) {
                    offScreenImage = createImage(getWidth(), getHeight());
                    offScreenGraphics = offScreenImage.getGraphics();
                }
                g.drawImage(offScreenImage, 0, 0, this);

                if (hoverX >= 0 && hoverY >= 0) {
                    drawSquare(g, hoverX, hoverY, paintColor, false);
                }
            }
        };
        drawPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
        drawPanel.setBackground(Color.WHITE);

        drawPanel.addMouseListener(this);
        drawPanel.addMouseMotionListener(this);
        drawPanel.addKeyListener(this);
        drawPanel.setFocusable(true);
        this.setResizable(false);

        this.add(drawPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showSaveConfirmation();
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    private void drawSquare(Graphics g, int x, int y, Color color, boolean permanent) {
        g.setColor(color);
        int gridX = (x / SQUARE_SIZE);
        int gridY = (y / SQUARE_SIZE);
        if (permanent) {
            offScreenGraphics.setColor(paintColor);
            offScreenGraphics.fillRect(gridX * SQUARE_SIZE, gridY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            this.tiles.addTile(gridX, gridY, paintColor);
        }
        g.fillRect(gridX * SQUARE_SIZE, gridY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Graphics g = drawPanel.getGraphics();
            drawSquare(g, e.getX(), e.getY(), this.paintColor, true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hoverX = e.getX();
        hoverY = e.getY();
        drawPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hoverX = -1;
        hoverY = -1;
        drawPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_2:
                this.paintColor = Color.RED;
                break;
            case KeyEvent.VK_3:
                this.paintColor = Color.BLUE;
                break;
            case KeyEvent.VK_4:
                this.paintColor = Color.MAGENTA;
                break;
            case KeyEvent.VK_5:
                this.paintColor = Color.PINK;
                break;
            case KeyEvent.VK_C:
                this.paintColor = Color.ORANGE;
                break;
            case KeyEvent.VK_M:
                this.paintColor = Color.GREEN;
                break;
            case KeyEvent.VK_6:
                this.paintColor = Color.CYAN;
                break;
            case KeyEvent.VK_0:
                this.paintColor = Color.WHITE;
                break;
            default:
                this.paintColor = Color.BLACK;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LevelBuilder::new);
    }

    public void saveLevel() {
        System.out.println("Save");
        try {
            FileOutputStream fos = new FileOutputStream("Level.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.tiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showSaveConfirmation() {
        int option = JOptionPane.showConfirmDialog(
                LevelBuilder.this,
                "Do you want to save the level design?",
                "Save Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            saveLevel();
            System.out.println("Bye");
            System.exit(0); // Exit after saving
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0); // Exit without saving
        }
    }
}
