package equipment;

import sprite.Player;

public class T1AtkRing extends Item{
	
	
	public T1AtkRing(Player player) {
		super(player, "src/Rings/T1AtkRing.png", "ring", true);
		attack = 4;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Attack \nof Character by 4)";
	}
	

}
