package equipment;

import sprite.Player;

public class T3ManaRing extends Item {

	public T3ManaRing(Player player) {
		super(player, "src/Rings/T3ManaRing.png", "ring", true);
		mana = 180;
	}

	public String getInfo() {
		return "Increases Max Mana \nof Character by 180)";
	}

}
