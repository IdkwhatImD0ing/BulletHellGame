package equipment;

import sprite.Player;

public class T3AtkRing extends Item {

	public T3AtkRing(Player player) {
		super(player, "src/Rings/T3AtkRing.png", "ring", true);
		attack = 12;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Attack \nof Character by 12)";
	}

}
