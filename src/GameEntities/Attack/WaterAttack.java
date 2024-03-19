package GameEntities.Attack;

import GameEntities.Elemental.WaterState;
import Main.GamePanel;

public class WaterAttack extends Attack {
    public WaterAttack(int x, int y, String direction, GamePanel gp, int owner) {
        super(x, y, direction, new WaterState(), gp,"res/attack-blue.png", owner);
    }
}
