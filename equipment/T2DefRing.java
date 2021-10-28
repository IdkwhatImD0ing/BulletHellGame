package equipment;

import sprite.Player;

public class T2DefRing extends Item {

	public T2DefRing(Player player) {
		super(player, "src/Rings/T2DefRing.png", "ring", true);
		defense = 8;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Defense \nof Character by 8)";
	}

}
