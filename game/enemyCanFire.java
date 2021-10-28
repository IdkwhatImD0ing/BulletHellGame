package game;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.TimerTask;

import enemies.Enemy;
import sprite.Player;

/**
 * Timer task method for enemies
 * 
 * @author Bill Zhang
 *
 */
public class enemyCanFire extends TimerTask {

	private Player targetPlayer;
	private Enemy thisEnemy;

	/**
	 * Constructor
	 * 
	 * @param me     this enemy
	 * @param player target player
	 * @param board  board enemy is on
	 */
	public enemyCanFire(Enemy me, Player player, Board board) {
		targetPlayer = player;
		thisEnemy = me;

	}

	/**
	 * Method called by timer Will fire a bullet at target
	 *
	 */
	public void run() {
		Point2D point = new Point();
		point.setLocation(targetPlayer.getX(), targetPlayer.getY());
		thisEnemy.fire(point);
	}

}
