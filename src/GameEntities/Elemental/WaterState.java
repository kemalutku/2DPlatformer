package GameEntities.Elemental;

public class WaterState extends ElementalState {
    public WaterState() {
        super("res/player-blue.png", "res/player2-blue.png");
        this.setElementName("water");
    }

    public WaterState(boolean isMonster) {
        super("res/monster-blue.png", "res/monster-blue.png");
        this.setElementName("fire");
    }
}
