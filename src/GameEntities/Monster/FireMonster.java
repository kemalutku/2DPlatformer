package GameEntities.Monster;

import GameEntities.Elemental.ElementalState;
import GameEntities.Elemental.FireState;
import Main.GamePanel;

import java.awt.*;

public class FireMonster extends Monster{
    public FireMonster(int x, int y, GamePanel gp) {
        super(x, y, gp, new FireState(true));
    }

}
