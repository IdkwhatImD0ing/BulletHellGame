package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**Def Pot Class
 * @author Bill Zhang
 *
 */
public class DefPot extends Pot{
	
	/**Constructor 
	 * @param board board this pot is on
	 */
	
	public DefPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/DefPot.png");
		initPot();
	}
	
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getDefense() < player.getMaxDef()) {
			player.increaseDef(1);
		}
	}
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player defense by 1";
	}
}
