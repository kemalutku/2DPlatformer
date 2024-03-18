package GameEntities.Attack;

import GameEntities.Elemental.WaterState;
import Main.GamePanel;

import java.awt.*;

import static Main.Settings.*;

public class WaterAttack extends Attack {
    public WaterAttack(int x, int y, String direction, GamePanel gp) {
        super(x, y, direction, new WaterState(), gp,"res/attack-blue.png");
    }
}
