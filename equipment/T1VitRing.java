package equipment;

import game.Board;
import sprite.Player;

public class T1VitRing extends Item {

	public T1VitRing(Player player) {
		super(player, "src/Rings/T1VitRing.png", "ring", true);
		vitality = 4;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Vit \nof Character by 4)";
	}

}
