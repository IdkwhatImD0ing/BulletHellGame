package equipment;

import sprite.Player;

public class T2Robe extends Item {

	public T2Robe(Player player) {
		super(player, "src/Armor/T2Robe.png", "armor", true);

		health = 40;
		mana = 60;
		wisdom = 8;
		vitality = 4;
		defense = 8;
		attack = 10;
		dexterity = 7;
		speed = 3;
	}

	public String getInfo() {
		return "Hp:" + health + " Mp: " + mana + " Atk: " + attack + " Def: " + defense + " Spd: " + speed + " Dex: "
				+ dexterity + " Vit " + vitality + " Wis: " + wisdom;
	}

}
