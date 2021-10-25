package enemies;

import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import game.Board;
import sprite.Player;
import sprite.Sprite;

/**Enemy main class
 * @author Bill Zhang
 *
 */
public class Enemy extends Sprite{
	private final double SPEED;
	private Player target;
	private String image;
	

    protected double currentX;
    protected double currentY;
    private double distanceX;
    private double distanceY;
    private double angle;

    
    private int health;
    private int maxHealth;
    private int defense;
    
    private Timer damageTimer;
	private boolean takeDamage;
	private int takeDamageInt;
    
	/**Constructor
	 * @param x position
	 * @param y position
	 * @param player Player enemy targeting
	 * @param imageName image of the enemy
	 * @param speed Speed of the enemy
	 * @param health health of the enemy
	 * @param defense defense of the enemy
	 * @param board board enemy is on
	 */
	public Enemy(int x, int y, Player player, String imageName, double speed, int health, int defense, Board board) {
		super(x,y, board);
		maxHealth = health;
		this.health = health;
		this.defense = defense;
		SPEED = speed;
		image = imageName;
		initEnemy();
		target = player;

		currentX = x;
		currentY = y;
		distanceX = target.getX() - currentX;
		distanceY = target.getY() - currentY;
			
			
	}
	
	/**Initializes the enemy
	 * 
	 */
	public void initEnemy() {
		loadImage(image);
		getImageDimensions();
	}
	
	/**Distance from player x
	 * @return distance
	 */
	public double getDistanceX() {
		return distanceX;
	}
	
	/**Y distance from player
	 * @return distance
	 */
	public double getDistanceY() {
		return distanceY;
	}
	
	/**Get the target
	 * @return Target
	 */
	public Player getTarget() {
		return target;
	}
	
	/**Gets the angle between player and this enemy
	 * @return the angle
	 */
	public double getTargetAngle() {
		return Math.atan2(distanceY, distanceX);
	}
	
	/**Take damage function
	 * @param damage damage to take
	 */
	public void takeDamage(int damage) {
		if (defense > damage || (damage - defense)/(double)damage < 0.2)
		{
			health = (int) (health - (damage * 0.2));
			takeDamageInt = (int) (health - (damage * 0.2));
		}
		else{
			health = health - (damage - defense);
			takeDamageInt = damage;
		}
		
		takeDamage = true;
		damageTimer = new Timer();
		damageTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				takeDamage = false;
				damageTimer.cancel();
				
			}
		}, 250);
	}
	
	/**Move funtion
	 * 
	 */
	public void move() {
		
		distanceX = target.getX() - currentX;
		distanceY = target.getY() - currentY;
		
		
		
		angle = Math.atan2(distanceY,distanceX);
		
		x += SPEED * Math.cos(angle);
        y += SPEED * Math.sin(angle);
        
        currentX = x;
        currentY = y;
		
	}
	
	/**Fire function to call for extended classes
	 * @param point Point to fire at
	 */
	public void fire(Point2D point) {
		return;
	}
	
	/**Gets the health of this enemy
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	
	/**Gets the max health of this enemy
	 * @return max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	
	/**Die function
	 * 
	 */
	public void die() {
		visible = false;
	}
	
	/**Checks if taking damage
     * @return yes or no
     */
    public boolean getTakeDamage() {
    	return takeDamage;
    }
    
    
    /**Returns the damage the player took
     * @return Damage from attack
     */
    public int getDamageTaken() {
    	return takeDamageInt;
    }
	
	
	
	
}
