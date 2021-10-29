package equipment;

import sprite.Player;

public class T3HpRing extends Item {

	public T3HpRing(Player player) {
		super(player, "src/Rings/T3HpRing.png", "ring", true);
		health = 180;
	}

	public String getInfo() {
		return "Increases Max Health \nof Character by 180)";
	}

}
