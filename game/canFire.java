package game;


import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.TimerTask;

import javax.swing.JFrame;

import sprite.Player;

/**Timer task method to control firing rate
 * @author Bill Zhang
 *
 */
public class canFire extends TimerTask{
	

	private Player player;
	private Board board;
	private JFrame jFrame;
	
	/**Constructor
	 * @param player player this timertask belongs too
	 * @param board board the player is on
	 */
	public canFire(Player player, Board board) {
		this.player = player;
		this.board = board;
		jFrame = board.getJFrame();
	}
	
	/**The executable method that is called by timer
	 *
	 */
	public void run() {
		Point2D mousePoint = MouseInfo.getPointerInfo().getLocation();
		mousePoint.setLocation(mousePoint.getX() - jFrame.getX(), mousePoint.getY() - jFrame.getY());
		player.fire(mousePoint);
	}
	

}
