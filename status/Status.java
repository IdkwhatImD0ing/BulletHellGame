package status;

import java.util.Timer;
import java.util.TimerTask;

import sprite.Player;
import sprite.Sprite;

/**Main Bag class
 * @author Bill Zhang
 *
 */
public class Status {
	
	public Sprite[] contents;
	public String image;
	private Timer timer;

	/**
	 * @param x x position of bag
	 * @param y y position
	 * @param board board this bag is on
	 * @param image image of bag
	 */
	public Status(double duration, Player player) {
		timer = new Timer();
		applyStatus(player);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				removeStatus(player);
				
			}
		}, 10000);
	}
	
	public void applyStatus(Player player) {
		return;
	}
	
	public void removeStatus(Player player) {
		return;
	}
	
}
