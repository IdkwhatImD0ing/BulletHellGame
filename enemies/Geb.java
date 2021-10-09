package enemies;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.lang.annotation.Target;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import bags.Bag;
import bags.BlueBag;
import bags.CyanBag;
import bags.WhiteBag;
import equipment.*;
import game.Board;
import game.enemyCanFire;
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

/**Class for one of the bosses
 * @author Bill Zhang
 *
 */
public class Geb extends Enemy{
	
	private final double ANGLE = Math.PI/6.0;
	private Random rand;

	private Player targetPlayer;
    private Timer timer;
    private Timer timer2;
    private Timer timer3;
    private boolean isTimerOn;
    private Point point;
    /**Constructor
     * @param x x position
     * @param y y position
     * @param player player
     * @param board the board this sprite is on
     */
    public Geb(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Boss/Geb.png", 1.5, 5000, 20, board);
		targetPlayer = player;
		point = new Point();
		
		timer = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();
		timer3.schedule(new TimerTask() {
			
			@Override
			public void run() {
				board.getEnemies().add(new GebArtifact((int)currentX + 15 , (int)currentY + 15, targetPlayer, board));
				board.getEnemies().add(new GebArtifact((int)currentX - 15, (int)currentY + 15, targetPlayer, board));
				board.getEnemies().add(new GebArtifact((int)currentX, (int)currentY - 21, targetPlayer, board));
				// TODO Auto-generated method stub
				
			}
		}, 0, 10000);
		isTimerOn = false;
		rand = new Random();
		point = new Point();
		// TODO Auto-generated constructor stub
	}
	
	/**Move function, moves towards player
	 *
	 */
	public void move() {
		
		point.setLocation(targetPlayer.getX(), targetPlayer.getY());
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				timer = new Timer();
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						
						fire(point);
						
					}
				}, 0, 2000);
				timer2.schedule(new TimerTask() {
					
					@Override
					public void run() {
						
						fire2();
						
					}
				}, 0, 500);
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
	
	
	/**Fire fuction
	 * Fires 3 bullets
	 */
	public void fire(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, point.getX(), point.getY(), "src/Enemies/Boss/GebBullet.png", 2000, 8, 150));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() + ANGLE, "src/Enemies/Boss/GebBullet.png", 2000, 8, 150));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, getTargetAngle() - ANGLE, "src/Enemies/Boss/GebBullet.png", 2000, 8, 150));

			
			

		}
    }
	
	/**Second fire function
	 * Fires ring of bullets
	 */
	public void fire2() {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 2 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 3 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 4 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 5 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 6 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 7 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 8 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 9 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 10 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 11 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, 0 + 12 * ANGLE, "src/Enemies/Boss/GebBullet2.png", 2000, 7, 30));
			
			

		}
    }

	
	/**Die function
	 * Gives xp
	 * Drops loot when dead
	 */
	public void die() {
		
		if (!visible) {
			return;
		}
		
		getTarget().gainExp(2000);
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
				}
				else if (rng3 < 0.25) {
					loot.add(new T3SpeedRing(getTarget()));
				}
				else if (rng3 < 0.375) {
					loot.add(new T3DefRing(getTarget()));
				}
				else if (rng3 < 0.5) {
					loot.add(new T3WisRing(getTarget()));
				}
				else if (rng3 < 0.625) {
					loot.add(new T3VitRing(getTarget()));
				}
				else if (rng3 < 0.75) {
					loot.add(new T3DexRing(getTarget()));
				}
				else if (rng3 < 0.875) {
					loot.add(new T3HpRing(getTarget()));
				}
				else if (rng3 < 1) {
					loot.add(new T3ManaRing(getTarget()));
				}
				loot.add(new LifePot(board));
			}
			else if (rng2 < 0.5) {
				loot.add(new T3Robe(getTarget()));
				loot.add(new LifePot(board));
			}
			else if (rng2 < 0.75) {
				loot.add(new T3Spell(getTarget()));
				loot.add(new ManaPot(board));
			}
			else {
				loot.add(new T3Staff(getTarget()));
				loot.add(new ManaPot(board));
			}	
		}
		else{
			loot = new CyanBag(x, y, board);
			double rng2 = rand.nextDouble();
			if (rng2 < 0.25) {
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
			else if (rng2 < 0.5) {
				loot.add(new T2Robe(getTarget()));
			}
			else if (rng2 < 0.75) {
				loot.add(new T2Spell(getTarget()));
			}
			else {
				loot.add(new T2Staff(getTarget()));
			}
		}
		
		double rng4 = rand.nextDouble();
		if (rng4 < 0.125) {
			loot.add(new SpdPot(board));
		}
		else if (rng4 < 0.25) {
			loot.add(new AtkPot(board));
		}
		else if (rng4 < 0.375) {
			loot.add(new DefPot(board));
		}
		else if (rng4 < 0.5) {
			loot.add(new DexPot(board));
		}
		else if (rng4 < 0.625) {
			loot.add(new VitPot(board));
		}
		else if (rng4 < 0.75) {
			loot.add(new WisPot(board));
		}
		else if (rng4 < 0.875) {
			loot.add(new LifePot(board));
		}
		else if (rng4 < 1) {
			loot.add(new ManaPot(board));
		}
		super.die();
		timer3.cancel();
		board.getBags().add(loot);
		
	}

	
}
