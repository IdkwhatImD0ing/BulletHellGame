package equipment;

import java.awt.Toolkit;

import sprite.Player;

public class T2Staff extends Item {

	public T2Staff(Player player) {
		super(player, "src/Weapons/T2Staff.png", "weapon", true);
		attack = 35;
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/PurpleBall.png");
	}

	public String getInfo() {
		return "Attack: " + attack;
	}

}
