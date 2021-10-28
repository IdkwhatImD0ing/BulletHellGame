package equipment;

import game.Board;
import sprite.Player;

public class T1ManaRing extends Item {

	public T1ManaRing(Player player) {
		super(player, "src/Rings/T1ManaRing.png", "ring", true);
		mana = 100;
	}

	public String getInfo() {
		return "Increases Max Mana \nof Character by 100)";
	}

}
