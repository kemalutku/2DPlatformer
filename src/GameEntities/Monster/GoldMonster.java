package GameEntities.Monster;

import GameEntities.Elemental.GoldState;
import Main.GamePanel;

import java.awt.*;

public class GoldMonster extends Monster{
    public GoldMonster(int x, int y, GamePanel gp) {
        super(x, y, gp, new GoldState(true));
    }
}
