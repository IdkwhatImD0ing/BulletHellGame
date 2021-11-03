package enemies;

import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Timer;

import bags.Bag;
import bags.BrownBag;
import game.Board;
import game.enemyCanFire;
import pots.HealthPot;
import pots.MpPot;
import sprite.Player;
import sprite.Projectile;

public class ZombieArcher extends Enemy {

	private Random rand;

	/**
	 * @param x      position
	 * @param y      position
	 * @param player target
	 * @param board  board enemy is on
	 */
	public ZombieArcher(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Zombie/ZombieArcher.png", 1.2, 100 + 10 * board.getRound(), 5, board);
		canFire = new Timer();
		isTimerOn = false;
		rand = new Random();
	}

	/**
	 * Move function. Will move towards target and fire
	 *
	 */
	public void move() {
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				canFire = new Timer();
				canFire.schedule(new enemyCanFire(this, getTarget(), board), 0, 500);
				isTimerOn = true;
			}
		} else {
			if (isTimerOn) {
				canFire.cancel();
				isTimerOn = false;
			}
		}
	}

	/**
	 * Fire function Fires bullets
	 */
	public void fire(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, point.getX(),
					point.getY(), "src/Enemies/Zombie/Bullet2.png", 500, 2, 10 + 2 * board.getRound()));

		}
	}

	/**
	 * Drops loot and xp when dead
	 *
	 */
	public void die() {
		if (!visible) {
			return;
		}
		getTarget().gainExp(20);
		double rng;
		if (rand.nextDouble() < 0.5) {
			Bag loot = new BrownBag(x, y, board);
			rng = rand.nextDouble();
			if (rng < 0.5) {
				loot.add(new HealthPot(board));
				board.getBags().add(loot);
			} else if (rng < 1) {
				loot.add(new MpPot(board));
				board.getBags().add(loot);

			}
		}
		super.die();

	}

}
