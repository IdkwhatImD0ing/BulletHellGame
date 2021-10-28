package equipment;

import game.Board;
import sprite.Player;

public class T2VitRing extends Item {

	public T2VitRing(Player player) {
		super(player, "src/Rings/T2VitRing.png", "ring", true);
		vitality = 8;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Vit \nof Character by 8)";
	}

}
