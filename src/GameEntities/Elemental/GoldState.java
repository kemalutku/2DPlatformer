package GameEntities.Elemental;

public class GoldState extends ElementalState{
    public GoldState() {
        super("res/player.png", "res/player2.png");
        this.elementName = "gold";
    }
    public GoldState(boolean isMonster) {
        super("res/monster-orange.png", "res/monster-orange.png");
        this.elementName = "gold";
    }
}
