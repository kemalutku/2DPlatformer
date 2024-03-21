package GameEntities.Elemental;

public class FireState extends ElementalState {
    public FireState() {
        super("res/player.png", "res/player2.png");
        this.setElementName("fire");
    }

    public FireState(boolean isMonster) {
        super("res/monster-red.png", "res/monster-red.png");
        this.setElementName("fire");
    }
}
