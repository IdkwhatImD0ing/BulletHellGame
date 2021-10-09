package equipment;

import game.Board;
import sprite.Player;

public class T2WisRing extends Item{
	
	
	public T2WisRing(Player player) {
		super(player, "src/Rings/T2WisRing.png", "ring", true);
		wisdom = 8;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Wisdom \nof Character by 8)";
	}
	

}
