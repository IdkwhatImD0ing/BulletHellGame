package bags;

import java.util.Timer;
import java.util.TimerTask;

import game.Board;
import sprite.Sprite;

/**
 * Main Bag class
 * 
 * @author Bill Zhang
 *
 */
public class Bag extends Sprite {

	public Sprite[] contents;
	public String image;
	private Timer timer;

	/**
	 * @param x     x position of bag
	 * @param y     y position
	 * @param board board this bag is on
	 * @param image image of bag
	 */
	public Bag(double x, double y, Board board, String image) {
		super(x, y, board);
		this.image = image;
		contents = new Sprite[] { null, null, null, null, null, null, null, null };
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				visible = false;

			}
		}, 10000);
	}

	/**
	 * Adds the item to the inventory
	 * 
	 * @param item item to be added
	 */
	public void add(Sprite item) {
		for (int x = 0; x < 8; x++) {
			if (contents[x] == null) {
				contents[x] = item;
				return;
			}
		}
	}

	/**
	 * Initializes the bag
	 * 
	 */
	public void initBag() {
		loadImage(image);
		getImageDimensions();
	}

	/**
	 * Removes the item from the inventory
	 * 
	 * @param index index of item to remove
	 */
	public void removeItem(int index) {
		contents[index] = null;
		if (isEmpty()) {
			visible = false;
		}
	}

	/**
	 * Checks if the bag is empty or not
	 * 
	 * @return True if empty
	 */
	public boolean isEmpty() {
		for (int x = 0; x < 8; x++) {
			if (contents[x] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the item at the index
	 * 
	 * @param index index of item
	 * @return The item
	 */
	public Sprite getItem(int index) {
		return contents[index];
	}

}
