package bags;

import bags.Bag;
import game.Board;

/**
 * Bluebag Class, Blue Bags contain pots
 * 
 * @author Bill Zhang
 *
 */
public class BlueBag extends Bag {

	/**
	 * Constructor
	 * 
	 * @param x     x position
	 * @param y     y position
	 * @param board board of bagx`
	 */
	public BlueBag(double x, double y, Board board) {
		super(x, y, board, "src/Bags/BlueBag.png");
		initBag();
	}

}
