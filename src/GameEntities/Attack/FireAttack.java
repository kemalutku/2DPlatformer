package GameEntities.Attack;

import GameEntities.Elemental.FireState;
import Main.GamePanel;

public class FireAttack extends Attack {
    public FireAttack(int x, int y, String direction, GamePanel gp, int owner) {
        super(x, y, direction, new FireState(), gp, "res/attack.png", owner);
    }
}
