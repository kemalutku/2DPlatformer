package GameEntities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean firePressed, waterPressed, spacePressed;
    public boolean attackPressed;
    public boolean pauseTyped = false;
    public boolean savePressed = false, loadPressed = false;
    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyChar();
        if (code == 112 || code == 80) {
            pauseTyped = !pauseTyped;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_1:
                firePressed = true;
                break;
            case KeyEvent.VK_2:
                waterPressed = true;
                break;
            case KeyEvent.VK_3:
                spacePressed = true;
                break;
            case KeyEvent.VK_SPACE:
                attackPressed = true;
                break;
            case KeyEvent.VK_K:
                savePressed = true;
                break;
            case KeyEvent.VK_L:
                loadPressed = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_1:
                firePressed = false;
                break;
            case KeyEvent.VK_2:
                waterPressed = false;
                break;
            case KeyEvent.VK_3:
                spacePressed = false;
                break;
            case KeyEvent.VK_SPACE:
                attackPressed = false;
                break;
            case KeyEvent.VK_K:
                savePressed = false;
                break;
            case KeyEvent.VK_L:
                loadPressed = false;
                break;
        }
    }
}
