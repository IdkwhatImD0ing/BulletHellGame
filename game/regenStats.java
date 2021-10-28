package game;

import java.util.TimerTask;

import sprite.Player;

/**
 * Timertask method for regenerating hp aand mana
 * 
 * @author Bill ZHang
 */
public class regenStats extends TimerTask {
	Player player;

	/**
	 * Constructor
	 * 
	 * @param player player to regen hp for
	 */
	public regenStats(Player player) {
		this.player = player;
	}

	/**
	 * Method called by timer
	 *
	 */
	public void run() {
		player.regenHp();
		player.regenMana();
	}
}
