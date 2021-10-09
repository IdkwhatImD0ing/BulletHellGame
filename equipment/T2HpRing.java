package equipment;

import game.Board;
import sprite.Player;

public class T2HpRing extends Item{
	
	
	public T2HpRing(Player player) {
		super(player, "src/Rings/T2HpRing.png", "ring", true);
		health = 140;
	}
	
	public String getInfo() {
		return "Increases Max Health \nof Character by 140)";
	}
	
}
