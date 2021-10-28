package pots;

import java.awt.Toolkit;

import game.Board;
import sprite.Player;

/**
 * Atk Pot Class
 * 
 * @author Bill Zhang
 *
 */
public class AtkPot extends Pot{
	/**Constructor 
	 * @param board board this pot is on
	 */
	public AtkPot(Board board) {
		super(board);
		image = Toolkit.getDefaultToolkit().createImage("src/Pots/AtkPot.png");
		initPot();
	}
	
	
	/** Use Pot fuction
	 * Player gains the stats of this pot
	 * @param player to use pot on
	 */
	public void usePot(Player player) {
		if (player.getAttack() < player.getMaxAtk()) {
			player.increaseAtk(1);
		}
		
	}
	/**Gets description of the pot
	 */
	public String getInfo() {
		return "Increase player attack by 1";
	}
}
