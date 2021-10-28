package game;

import java.util.TimerTask;

import sprite.Player;

/**
 * Timer task method to control firing rate
 * 
 * @author Bill Zhang
 *
 */
public class fireChanger extends TimerTask {

	private Player player;
	/**
	 * Constructor
	 * 
	 * @param player player this timertask belongs too
	 * @param board  board the player is on
	 */
	public fireChanger(Player player, Board board) {
		this.player = player;
		board.getJFrame();
	}

	/**
	 * The executable method that is called by timer
	 *
	 */
	public void run() {
		player.setFire(true);
	}

}
