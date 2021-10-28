package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**
 * Vit Pot Class
 * 
 * @author Bill Zhang
 *
 */
public class VitPot extends Pot {
	/**
	 * Constructor
	 * 
	 * @param board board this pot is on
	 */
	public VitPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/VitPot.png");
		initPot();
	}

	/**
	 * Use Pot fuction Player gains the stats of this pot
	 * 
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getVit() < player.getMaxVit()) {
			player.increaseVit(1);
		}
	}

	/**
	 * Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player vitality by 1";
	}
}
