package equipment;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

public class T3Staff extends Item{
	

	
	public T3Staff(Player player) {
		super(player, "src/Weapons/T3Staff.png", "weapon", true);
		attack = 65;
		bulletImage = Toolkit.getDefaultToolkit().createImage("src/Weapons/RedBall.png");
	}

	public String getInfo() {
		return "Attack: " + attack;
	}
	
}
