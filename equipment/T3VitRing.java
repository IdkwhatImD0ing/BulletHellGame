package equipment;

import game.Board;
import sprite.Player;

public class T3VitRing extends Item{
	
	
	public T3VitRing(Player player) {
		super(player, "src/Rings/T3VitRing.png", "ring", true);
		vitality = 12;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Vit \nof Character by 12)";
	}
	

}
