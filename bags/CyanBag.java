package bags;

import bags.Bag;
import game.Board;
/**CyanBag Class, Cyan Bags contain pots/t2 items
 * @author Bill Zhang
 *
 */
public class CyanBag extends Bag{
	/**Constructor
	 * @param x x position
	 * @param y y position
	 * @param board board of bagx`
	 */
	public CyanBag(double x, double y, Board board) {
		super(x, y, board, "src/Bags/CyanBag.png");
		initBag();
	}
	
	
}
