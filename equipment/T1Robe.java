package equipment;

import game.Board;
import sprite.Player;

public class T1Robe extends Item {

	public T1Robe(Player player) {
		super(player, "src/Armor/T1Robe.png", "armor", true);

		health = 20;
		mana = 40;
		wisdom = 5;
		vitality = 2;
		defense = 4;
		attack = 5;
		dexterity = 3;
		speed = 1;
		equipItem();
	}

	public String getInfo() {
		return "Hp:" + health + " Mp: " + mana + " Atk: " + attack + " Def: " + defense + " Spd: " + speed + " Dex: "
				+ dexterity + " Vit " + vitality + " Wis: " + wisdom;
	}

}
