package pots;

import equipment.Item;
import game.Board;
import sprite.Player;
import sprite.Sprite;

/**Main Pot class
 * @author Bill Zhang
 *
 */
public class Pot extends Sprite{
	/**Constructor 
	 * @param board board this pot is on
	 */
	public Pot(Board board) {
		super(0, 0, board);
	}
	
	/**INitializes the pot
	 * 
	 */
	public void initPot() {
		loadImage(image);
		getImageDimensions();
	}
	

	
	
}
