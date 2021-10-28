package enemies;

import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Timer;
import bags.*;
import equipment.*;
import game.Board;
import game.enemyCanFire;
import pots.*;
import sprite.Player;
import sprite.Projectile;

/**
 * Medusa Class
 * 
 * @author Bill Zhang
 *
 */
public class Medusa extends Enemy {

	private final double ANGLE = Math.PI / 6.0;
	private Random rand;

	private Timer timer;
	private boolean isTimerOn;

	/**
	 * @param x      position
	 * @param y      position
	 * @param player target
	 * @param board  board enemy is one
	 */
	public Medusa(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Medusa/Medusa.png", 1.5, 1000, 20, board);
		timer = new Timer();
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
				timer = new Timer();
				timer.schedule(new enemyCanFire(this, getTarget(), board), 0, 2000);
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
					point.getY(), "src/Enemies/Medusa/MedusaBullet.png", 700, 3, 25 + 3 * board.getRound()));
			board.getEnemyProjectiles()
					.add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, getTargetAngle() - ANGLE,
							"src/Enemies/Medusa/MedusaBullet.png", 700, 3, 25 + 3 * board.getRound()));
			board.getEnemyProjectiles()
					.add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, getTargetAngle() + ANGLE,
							"src/Enemies/Medusa/MedusaBullet.png", 700, 3, 25 + 3 * board.getRound()));
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

		getTarget().gainExp(300);
		double rng;
		Bag loot = new BlueBag(x, y, board);
		rng = rand.nextDouble();
		if (rng < 0.05) {
			loot = new WhiteBag(x, y, board);
			double rng2 = rand.nextDouble();
			if (rng2 < 0.25) {
				loot.add(new T3HpRing(getTarget()));
				double rng3 = rand.nextDouble();
				if (rng3 < 0.125) {
					loot.add(new T3AtkRing(getTarget()));
				} else if (rng3 < 0.25) {
					loot.add(new T3SpeedRing(getTarget()));
				} else if (rng3 < 0.375) {
					loot.add(new T3DefRing(getTarget()));
				} else if (rng3 < 0.5) {
					loot.add(new T3WisRing(getTarget()));
				} else if (rng3 < 0.625) {
					loot.add(new T3VitRing(getTarget()));
				} else if (rng3 < 0.75) {
					loot.add(new T3DexRing(getTarget()));
				} else if (rng3 < 0.875) {
					loot.add(new T3HpRing(getTarget()));
				} else if (rng3 < 1) {
					loot.add(new T3ManaRing(getTarget()));
				}
				loot.add(new LifePot(board));
			} else if (rng2 < 0.5) {
				loot.add(new T3Robe(getTarget()));
				loot.add(new LifePot(board));
			} else if (rng2 < 0.75) {
				loot.add(new T3Spell(getTarget()));
				loot.add(new ManaPot(board));
			} else {
				loot.add(new T3Staff(getTarget()));
				loot.add(new ManaPot(board));
			}
		} else if (rng < 0.5) {
			loot = new CyanBag(x, y, board);
			double rng2 = rand.nextDouble();
			if (rng2 < 0.25) {
				double rng3 = rand.nextDouble();
				if (rng3 < 0.125) {
					loot.add(new T2AtkRing(getTarget()));
				} else if (rng3 < 0.25) {
					loot.add(new T2SpeedRing(getTarget()));
				} else if (rng3 < 0.375) {
					loot.add(new T2DefRing(getTarget()));
				} else if (rng3 < 0.5) {
					loot.add(new T2WisRing(getTarget()));
				} else if (rng3 < 0.625) {
					loot.add(new T2VitRing(getTarget()));
				} else if (rng3 < 0.75) {
					loot.add(new T2DexRing(getTarget()));
				} else if (rng3 < 0.875) {
					loot.add(new T2HpRing(getTarget()));
				} else if (rng3 < 1) {
					loot.add(new T2ManaRing(getTarget()));
				}

			} else if (rng2 < 0.5) {
				loot.add(new T2Robe(getTarget()));
			} else if (rng2 < 0.75) {
				loot.add(new T2Spell(getTarget()));
			} else {
				loot.add(new T2Staff(getTarget()));
			}
		}

		double rng4 = rand.nextDouble();
		if (rng4 < 0.125) {
			loot.add(new SpdPot(board));
		} else if (rng4 < 0.25) {
			loot.add(new AtkPot(board));
		} else if (rng4 < 0.375) {
			loot.add(new DefPot(board));
		} else if (rng4 < 0.5) {
			loot.add(new DexPot(board));
		} else if (rng4 < 0.625) {
			loot.add(new VitPot(board));
		} else if (rng4 < 0.75) {
			loot.add(new WisPot(board));
		} else if (rng4 < 0.875) {
			loot.add(new LifePot(board));
		} else if (rng4 < 1) {
			loot.add(new ManaPot(board));
		}
		super.die();
		board.getBags().add(loot);

	}

}
