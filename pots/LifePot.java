package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**Life Pot Class
 * @author Bill Zhang
 *
 */
public class LifePot extends Pot{
	
	/**Constructor 
	 * @param board board this pot is on
	 */
	public LifePot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/LifePot.png");
		initPot();
	}
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getMaxHealth() < player.getMaximumHealth()) {
			player.increaseMaxHealth(5);
		}
	}
	
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player health by 5";
	}
}
