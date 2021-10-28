package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**
 * Health Pot Class
 * 
 * @author Bill Zhang
 *
 */
public class HealthPot extends Pot {
	/**
	 * Constructor
	 * 
	 * @param board board this pot is on
	 */
	public HealthPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/HealthPot.png");
		initPot();
	}

	/**
	 * Use Pot fuction Player gains the stats of this pot
	 * 
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		player.increaseHealth(100);
	}

	/**
	 * Gets description of the pot
	 */
	public String getInfo() {
		return "Instantly regain 100 hp";
	}
}
