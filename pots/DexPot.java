package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**
 * Dex Pot Class
 * 
 * @author Bill Zhang
 *
 */
public class DexPot extends Pot {
	/**
	 * Constructor
	 * 
	 * @param board board this pot is on
	 */
	public DexPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/DexPot.png");
		initPot();
	}

	/**
	 * Use Pot fuction Player gains the stats of this pot
	 * 
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getDex() < player.getMaxDex()) {
			player.increaseDex(1);
		}
	}

	/**
	 * Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player dexterity by 1";
	}
}
