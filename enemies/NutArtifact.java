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

public class NutArtifact extends Enemy {

	private Random rand;

	private Timer timer;
	private boolean isTimerOn;

	/**
	 * @param x      position
	 * @param y      position
	 * @param player target
	 * @param board  board enemy is on
	 */
	public NutArtifact(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Boss/NutArtifact.png", 2, 500, 5, board);
		timer = new Timer();
		isTimerOn = false;
		rand = new Random();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Move function. Will move towards target and fire
	 *
	 */
	public void move() {
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				timer = new Timer();
				timer.schedule(new enemyCanFire(this, getTarget(), board), 0, 1500);
				isTimerOn = true;
			}
		} else {
			if (isTimerOn) {
				timer.cancel();
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
					point.getY(), "src/Enemies/Boss/LightBlueBullet.png", 500, 4, 100));

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
		getTarget().gainExp(50);
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
