package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;


/**Mana Pot Class
 * @author Bill Zhang
 *
 */
public class ManaPot extends Pot{
	/**Constructor 
	 * @param board board this pot is on
	 */
	public ManaPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/ManaPot.png");
		initPot();
	}
	
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getMaxMana() < player.getMaximumMana()) {
			player.increaseMaxMana(5);
		}
	}
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player mana by 5";
	}
}
