package equipment;

import sprite.Player;

public class T2DexRing extends Item {

	public T2DexRing(Player player) {
		super(player, "src/Rings/T2DexRing.png", "ring", true);
		dexterity = 8;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Dex \nof Character by 8)";
	}

}
