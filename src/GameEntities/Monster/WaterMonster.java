package GameEntities.Monster;

import GameEntities.Elemental.FireState;
import GameEntities.Elemental.WaterState;
import Main.GamePanel;

import java.awt.*;

public class WaterMonster extends Monster{
    public WaterMonster(int x, int y, GamePanel gp) {
        super(x, y, gp, new WaterState(true));
    }

}
