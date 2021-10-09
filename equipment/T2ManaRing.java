package equipment;

import game.Board;
import sprite.Player;

public class T2ManaRing extends Item{
	
	
	public T2ManaRing(Player player) {
		super(player, "src/Rings/T2ManaRing.png", "ring", true);
		mana = 140;
	}
	

	public String getInfo() {
		return "Increases Max Mana \nof Character by 140)";
	}
}
