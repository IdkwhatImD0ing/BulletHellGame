package equipment;

import sprite.Player;

public class T3DexRing extends Item{
	
	
	public T3DexRing(Player player) {
		super(player, "src/Rings/T3DexRing.png", "ring", true);
		dexterity = 12;
		equipItem();
	}
	
	public String getInfo() {
		return "Increases Max Dex \nof Character by 12)";
	}
	

}
