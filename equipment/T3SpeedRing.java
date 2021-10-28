package equipment;

import game.Board;
import sprite.Player;

public class T3SpeedRing extends Item {

	public T3SpeedRing(Player player) {
		super(player, "src/Rings/T3SpdRing.png", "ring", true);
		speed = 12;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Speed \nof Character by 12)";
	}

}
