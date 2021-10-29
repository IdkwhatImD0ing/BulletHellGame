package bags;

import game.Board;

/**
 * WhiteBag item, contains T3 Weapons/pots
 * 
 * @author Bill Zhang
 *
 */
public class WhiteBag extends Bag {
	/**
	 * Constructor
	 * 
	 * @param x     x position
	 * @param y     y position
	 * @param board board of bagx`
	 */
	public WhiteBag(double x, double y, Board board) {
		super(x, y, board, "src/Bags/WhiteBag.png");
		initBag();
	}

}
