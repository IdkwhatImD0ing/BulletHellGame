package bags;

import bags.Bag;
import game.Board;
/**BrownBag class, contains dropped items or health/mp pots
 * @author Bill Zhang
 *
 */
public class BrownBag extends Bag{
	/**
	 * Constructor
	 * 
	 * @param x     x position
	 * @param y     y position
	 * @param board board of bagx`
	 */
	public BrownBag(double x, double y, Board board) {
		super(x, y, board, "src/Bags/BrownBag.png");
		initBag();
	}
	
	
}
