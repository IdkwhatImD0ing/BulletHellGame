package equipment;

import game.Board;
import sprite.Player;

public class T1WisRing extends Item{
	
	
	public T1WisRing(Player player) {
		super(player, "src/Rings/T1WisRing.png", "ring", true);
		wisdom = 4;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Wisdom \nof Character by 4)";
	}
	

}
