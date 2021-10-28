package equipment;

import sprite.Player;

public class T2AtkRing extends Item {

	public T2AtkRing(Player player) {
		super(player, "src/Rings/T2AtkRing.png", "ring", true);
		attack = 8;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Attack \nof Character by 8)";
	}

}
