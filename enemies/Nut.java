package enemies;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import bags.Bag;
import bags.BlueBag;
import bags.CyanBag;
import bags.WhiteBag;
import equipment.T2AtkRing;
import equipment.T2DefRing;
import equipment.T2DexRing;
import equipment.T2HpRing;
import equipment.T2ManaRing;
import equipment.T2Robe;
import equipment.T2SpeedRing;
import equipment.T2Spell;
import equipment.T2Staff;
import equipment.T2VitRing;
import equipment.T2WisRing;
import equipment.T3AtkRing;
import equipment.T3DefRing;
import equipment.T3DexRing;
import equipment.T3HpRing;
import equipment.T3ManaRing;
import equipment.T3Robe;
import equipment.T3SpeedRing;
import equipment.T3Spell;
import equipment.T3Staff;
import equipment.T3VitRing;
import equipment.T3WisRing;
import game.Board;
import pots.AtkPot;
import pots.DefPot;
import pots.DexPot;
import pots.LifePot;
import pots.ManaPot;
import pots.SpdPot;
import pots.VitPot;
import pots.WisPot;
import sprite.Player;
import sprite.Projectile;

public class Nut extends Enemy {

	private final double ANGLE = Math.PI / 12.0;
	private final double ANGLE2 = Math.PI / 6.0;
	private Random rand;

	private Player targetPlayer;
	private Timer timer;
	private Timer timer2;
	private Timer timer3;
	private Point point;
	private Point point2;

	/**
	 * @param x      position
	 * @param y      position
	 * @param player target
	 * @param board  board enemy is on
	 */
	public Nut(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Boss/Nut.png", 1.5, 4000, 20, board);
		targetPlayer = player;
		point = new Point();
		point2 = new Point();

		timer = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();
		timer3.schedule(new TimerTask() {

			@Override
			public void run() {
				board.getEnemies().add(new NutArtifact((int) currentX + 15, (int) currentY + 15, targetPlayer, board));
				board.getEnemies().add(new NutArtifact((int) currentX - 15, (int) currentY + 15, targetPlayer, board));
				board.getEnemies().add(new NutArtifact((int) currentX, (int) currentY - 21, targetPlayer, board));

			}
		}, 0, 10000);
		isTimerOn = false;
		rand = new Random();
		point = new Point();
	}

	/**
	 * Move function. Will move towards target and fire
	 *
	 */
	public void move() {

		point.setLocation(targetPlayer.getX(), targetPlayer.getY());
		point2.setLocation(0, 0);
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {

						fire(point);

					}
				}, 0, 300);
				timer2.schedule(new TimerTask() {

					@Override
					public void run() {

						fire2(point2);

					}
				}, 0, 500);
				isTimerOn = true;
			}
		} else {
			if (isTimerOn) {
				timer.cancel();
				timer2.cancel();
				isTimerOn = false;
			}
		}
	}

	/**
	 * Fire function Fires bullets
	 */
	public void fire(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, point2.getX(),
					point.getY(), "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() + ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() - ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() + 2 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() + 3 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() - 2 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() - 3 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() + 4 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2,
					getTargetAngle() - 4 * ANGLE, "src/Enemies/Boss/NutBullet.png", 2000, 4, 30));

		}
	}

	/**
	 * 2nd Fire function Fires bullets
	 */
	public void fire2(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 2 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 3 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 4 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 5 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 6 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 7 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 8 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 9 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 10 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 11 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth() / 2, y + getHeight() / 2, 0 + 12 * ANGLE2,
					"src/Enemies/Boss/NutBullet2.png", 2000, 7, 20));

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
		if (isTimerOn) {
			timer2.cancel();
			timer.cancel();
		}
		getTarget().gainExp(1000);
		double rng;
		Bag loot = new BlueBag(x, y, board);
		rng = rand.nextDouble();
		if (rng < 0.5) {
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
		} else {
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
		dieWithoutFire();
		timer3.cancel();
		board.getBags().add(loot);

	}

}
