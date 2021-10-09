package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;
/**Speed Pot Class
 * @author Bill Zhang
 *
 */
public class SpdPot extends Pot{
	/**Constructor 
	 * @param board board this pot is on
	 */
	public SpdPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/SpdPot.png");
		initPot();
	}
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getSpeed() < player.getMaxSpd()) {
			player.increaseSpd(1);
		}
	}
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player speed by 1";
	}
}
