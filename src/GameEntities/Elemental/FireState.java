package GameEntities.Elemental;

public class FireState extends ElementalState {
    public FireState() {
        super("res/player.png", "res/player2.png");
        this.elementName = "fire";
    }

    public FireState(boolean isMonster) {
        super("res/monster-red.png", "res/monster-red.png");
        this.elementName = "fire";
    }
}
