package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;


/**Wis Pot Class
 * @author Bill Zhang
 *
 */
public class WisPot extends Pot{
	/**Constructor 
	 * @param board board this pot is on
	 */
	
	public WisPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/WisPot.png");
		initPot();
	}
	
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getWis() < player.getMaxWis()) {
			player.increaseWis(1);
		}
	}
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player wisdom by 1";
	}
}
