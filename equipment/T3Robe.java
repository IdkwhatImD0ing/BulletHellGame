package equipment;

import game.Board;
import sprite.Player;

public class T3Robe extends Item{
	

	public T3Robe(Player player) {
		super(player, "src/Armor/T3Robe.png", "armor", true);
		
		health = 65;
		mana = 80;
		wisdom = 12;
		vitality = 8;
		defense = 14;
		attack = 15;
		dexterity = 12;
		speed = 5;
	}
	

	public String getInfo() {
		return "Hp:" + health + " Mp: " + mana
				+ " Atk: " + attack +  " Def: " + defense
				+ " Spd: " + speed + " Dex: " + dexterity
				+ " Vit " + vitality + " Wis: " + wisdom;
	}


}
