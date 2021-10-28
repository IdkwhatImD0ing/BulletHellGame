package equipment;

import game.Board;
import sprite.Player;

public class T1SpeedRing extends Item {

	public T1SpeedRing(Player player) {
		super(player, "src/Rings/T1SpeedRing.png", "ring", true);
		speed = 4;
		equipItem();
	}

	public String getInfo() {
		return "Increases Max Speed \nof Character by 4)";
	}

}
