package equipment;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

public class T1Staff extends Item {

	public T1Staff(Player player) {
		super(player, "src/Weapons/T1Staff.png", "weapon", true);
		attack = 20;
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/WhiteBall.png");
		equipItem();

	}

	public String getInfwo() {
		return "Attack: " + attack;
	}

}
