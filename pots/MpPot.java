package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**
 * Mana Pot Class
 * 
 * @author Bill Zhang
 *
 */
public class MpPot extends Pot {
	/**
	 * Constructor
	 * 
	 * @param board board this pot is on
	 */
	public MpPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/MpPot.png");
		initPot();
	}

	/**
	 * Use Pot fuction Player gains the stats of this pot
	 * 
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		player.increaseMana(100);
	}

	/**
	 * Gets description of the pot
	 */
	public String getInfo() {
		return "Instantly regain 100 mana";
	}
}
