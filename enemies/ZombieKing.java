package enemies;

import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Timer;

import bags.Bag;
import bags.BlueBag;
import bags.CyanBag;
import equipment.*;
import game.Board;
import game.enemyCanFire;
import pots.AtkPot;
import pots.DefPot;
import pots.DexPot;
import pots.SpdPot;
import pots.VitPot;
import pots.WisPot;
import sprite.Player;
import sprite.Projectile;

public class ZombieKing extends Enemy{
	
	private final double ANGLE = Math.PI/6.0;
	private Random rand;

    private Timer timer;
    private boolean isTimerOn;
    
    /**
	 * @param x position
	 * @param y position
	 * @param player target
	 * @param board board enemy is on
	 */
	public ZombieKing(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Zombie/ZombieKing.png", 1, 400 + 10 * board.getRound(), 5, board);
		timer = new Timer();
		isTimerOn = false;
		rand = new Random();
		// TODO Auto-generated constructor stub
	}
	/**Move function. Will move towards target and fire
	 *
	 */
	public void move() {
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				timer = new Timer();
				timer.schedule(new enemyCanFire(this, getTarget(), board), 0, 1000);
				isTimerOn = true;
			}
		}
		else {
			if(isTimerOn) {
				timer.cancel();
				isTimerOn = false;
			}
		}
	}
	
	/**Fire function
	 * Fires bullets
	 */
	public void fire(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, point.getX(), point.getY(), "src/Enemies/Zombie/Bullet3.png", 700, 2, 15 + 2 * board.getRound()));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() - ANGLE, "src/Enemies/Zombie/Bullet3.png", 700, 2, 15 + 2 * board.getRound()));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() + ANGLE, "src/Enemies/Zombie/Bullet3.png", 700, 2, 15 + 2 * board.getRound()));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() + 2 * ANGLE, "src/Enemies/Zombie/Bullet3.png", 700, 2, 15 + 2 * board.getRound()));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() - 2 *  ANGLE, "src/Enemies/Zombie/Bullet3.png", 700, 2, 15 + 2 * board.getRound()));
		}
    }

	/**Drops loot and xp when dead
	 *
	 */
	public void die() {
		
		getTarget().gainExp(50);
		double rng;
		if (rand.nextDouble() < 1 && visible) {
			Bag loot = new BlueBag(x, y, board);
			rng = rand.nextDouble();
			if (rng < 0.15) {
				loot.add(new SpdPot(board));
			}
			else if (rng < 0.3) {
				loot.add(new DexPot(board));
			}
			else if (rng < 0.4) {
				loot.add(new AtkPot(board));
			}
			else if (rng < 0.5) {
				loot.add(new DefPot(board));
			}
			else if (rng < 0.7) {
				loot.add(new VitPot(board));
			}
			else if (rng < 0.9) {
				loot.add(new WisPot(board));
			}
			else if (rng < 0.925) {
				loot = new CyanBag(x,y,board);
				double rng3 = rand.nextDouble();
				if (rng3 < 0.125) {
					loot.add(new T2AtkRing(getTarget()));
				}
				else if (rng3 < 0.25) {
					loot.add(new T2SpeedRing(getTarget()));
				}
				else if (rng3 < 0.375) {
					loot.add(new T2DefRing(getTarget()));
				}
				else if (rng3 < 0.5) {
					loot.add(new T2WisRing(getTarget()));
				}
				else if (rng3 < 0.625) {
					loot.add(new T2VitRing(getTarget()));
				}
				else if (rng3 < 0.75) {
					loot.add(new T2DexRing(getTarget()));
				}
				else if (rng3 < 0.875) {
					loot.add(new T2HpRing(getTarget()));
				}
				else if (rng3 < 1) {
					loot.add(new T2ManaRing(getTarget()));
				}
			}
			else if (rng < 0.95) {
				loot = new CyanBag(x,y,board);
				loot.add(new T2Robe(board.getPlayer()));
			}
			else if (rng < 0.975) {
				loot = new CyanBag(x,y,board);
				loot.add(new T2Spell(board.getPlayer()));
			}
			else if (rng < 1) {
				loot = new CyanBag(x,y,board);
				loot.add(new T2Staff(board.getPlayer()));
			}
			board.getBags().add(loot);
		}
		super.die();
		
	}

	
}
