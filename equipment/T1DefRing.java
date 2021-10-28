package equipment;

import sprite.Player;

public class T1DefRing extends Item {

	public T1DefRing(Player player) {
		super(player, "src/Rings/T1DefRing.png", "ring", true);
		defense = 4;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Defense \nof Character by 4)";
	}

}
