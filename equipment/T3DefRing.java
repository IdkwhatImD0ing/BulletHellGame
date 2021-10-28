package equipment;

import sprite.Player;

public class T3DefRing extends Item {

	public T3DefRing(Player player) {
		super(player, "src/Rings/T3DefRing.png", "ring", true);
		defense = 12;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Defense \nof Character by 12)";
	}

}
