package equipment;

import sprite.Player;

public class T1HpRing extends Item {

	public T1HpRing(Player player) {
		super(player, "src/Rings/T1HpRing.png", "ring", true);
		health = 100;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Health \nof Character by 100)";
	}

}
