package equipment;

import game.Board;
import sprite.Player;

public class T2SpeedRing extends Item{
	
	
	public T2SpeedRing(Player player) {
		super(player, "src/Rings/T2SpdRing.png", "ring", true);
		speed = 8;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Speed \nof Character by 8)";
	}
	

}
