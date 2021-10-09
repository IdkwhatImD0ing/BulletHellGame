package equipment;

import sprite.Player;

public class T1DexRing extends Item{
	
	
	public T1DexRing(Player player) {
		super(player, "src/Rings/T1DexRing.png", "ring", true);
		dexterity = 4;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Dex \nof Character by 4)";
	}
	

}
