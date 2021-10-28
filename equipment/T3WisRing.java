package equipment;

import game.Board;
import sprite.Player;

public class T3WisRing extends Item {

	public T3WisRing(Player player) {
		super(player, "src/Rings/T3WisRing.png", "ring", true);
		wisdom = 8;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Wisdom \nof Character by 8)";
	}

}
