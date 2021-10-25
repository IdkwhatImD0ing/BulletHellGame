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

/**Minion of one of the bosses
 * @author Bill Zhang
 *
 */
public class BesArtifact extends Enemy{
	
	private final double ANGLE = Math.PI/6.0;
	private Random rand;

    private Timer timer;
    private boolean isTimerOn;
	/**Constructor
	 * @param x x position
	 * @param y y position
	 * @param player player the enemy is targeting
	 * @param board board of the enemy
	 */
	public BesArtifact(int x, int y, Player player, Board board) {
		super(x, y, player, "src/Enemies/Boss/BesArtifact.png", 2, 500, 5, board);
		timer = new Timer();
		isTimerOn = false;
		rand = new Random();
		// TODO Auto-generated constructor stub
	}
	
	/**Move function
	 *
	 */
	public void move() {
		super.move();
		if (Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2)) <= 1000) {
			if (!isTimerOn) {
				timer = new Timer();
				timer.schedule(new enemyCanFire(this, getTarget(), board), 0, 500);
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
	 *
	 */
	public void fire(Point2D point) {
		if (visible) {
			board.getEnemyProjectiles().add(new Projectile(x + getWidth()/2, y + getHeight()/2, point.getX(), point.getY(), "src/Enemies/Boss/BlackBullet.png", 500, 3, 50));
			
		}
    }

	
	/**Die function
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
			}
			else if (rng < 1) {
				loot.add(new MpPot(board));
				board.getBags().add(loot);

			}
		}
		super.die();
		
		
	}

	
}
