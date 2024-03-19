package GameEntities.KeyHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed1, downPressed1, leftPressed1, rightPressed1;
    public boolean firePressed1, waterPressed1, spacePressed1;
    public boolean attackPressed1;

    public boolean upPressed2, downPressed2, leftPressed2, rightPressed2;
    public boolean firePressed2, waterPressed2, spacePressed2;
    public boolean attackPressed2;


    public boolean pauseTyped = false;
    public boolean savePressed = false, loadPressed = false;
    public boolean versusTyped = false;


    public KeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyChar();
        if (code == 112 || code == 80) {
            pauseTyped = !pauseTyped;
        } else if (code == 118 || code == 86) {
            versusTyped = !versusTyped;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed1 = true;
                break;
            case KeyEvent.VK_A:
                leftPressed1 = true;
                break;
            case KeyEvent.VK_S:
                downPressed1 = true;
                break;
            case KeyEvent.VK_D:
                rightPressed1 = true;
                break;
            case KeyEvent.VK_1:
                firePressed1 = true;
                break;
            case KeyEvent.VK_2:
                waterPressed1 = true;
                break;
            case KeyEvent.VK_3:
                spacePressed1 = true;
                break;
            case KeyEvent.VK_SPACE:
                attackPressed1 = true;
                break;
            case KeyEvent.VK_K:
                savePressed = true;
                break;
            case KeyEvent.VK_L:
                loadPressed = true;
                break;
            case KeyEvent.VK_UP:
                upPressed2 = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed2 = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed2 = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed2 = true;
                break;
            case KeyEvent.VK_NUMPAD1:
                firePressed2 = true;
                break;
            case KeyEvent.VK_NUMPAD2:
                waterPressed2 = true;
                break;
            case KeyEvent.VK_NUMPAD3:
                spacePressed2 = true;
                break;
            case KeyEvent.VK_NUMPAD0:
                attackPressed2 = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                upPressed1 = false;
                break;
            case KeyEvent.VK_A:
                leftPressed1 = false;
                break;
            case KeyEvent.VK_S:
                downPressed1 = false;
                break;
            case KeyEvent.VK_D:
                rightPressed1 = false;
                break;
            case KeyEvent.VK_1:
                firePressed1 = false;
                break;
            case KeyEvent.VK_2:
                waterPressed1 = false;
                break;
            case KeyEvent.VK_3:
                spacePressed1 = false;
                break;
            case KeyEvent.VK_SPACE:
                attackPressed1 = false;
                break;
            case KeyEvent.VK_K:
                savePressed = false;
                break;
            case KeyEvent.VK_L:
                loadPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed2 = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed2 = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed2 = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed2 = false;
                break;
            case KeyEvent.VK_NUMPAD1:
                firePressed2 = false;
                break;
            case KeyEvent.VK_NUMPAD2:
                waterPressed2 = false;
                break;
            case KeyEvent.VK_NUMPAD3:
                spacePressed2 = false;
                break;
            case KeyEvent.VK_NUMPAD0:
                attackPressed2 = false;
                break;
        }
    }

}

