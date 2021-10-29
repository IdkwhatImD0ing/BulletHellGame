package sprite;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import equipment.Item;
import equipment.T1HpRing;
import equipment.T1Robe;
import equipment.T1Spell;
import equipment.T1Staff;
import game.Board;

/**
 * One of the player classes
 * 
 * @author Bill ZHang
 *
 */
public class Wizard extends Player {

	private final static double ANGLE = Math.PI / 6;
	private int abilityCost;

	/**
	 * Constructor
	 * 
	 * @param x     position
	 * @param y     position
	 * @param board board this player is on
	 */
	public Wizard(int x, int y, Board board) {
		super(x, y, board, "src/Wizard/WizardRight.png", "src/Wizard/WizardLeft.png", "src/Wizard/WizardDown.png",
				"src/Wizard/WizardUp.png", "src/Wizard/WizardMovingRight.gif", "src/Wizard/WizardMovingLeft.gif",
				"src/Wizard/WizardMovingUp.gif", "src/Wizard/WizardMovingDown.gif");

		attack = 13;
		defense = 5;
		speed = 14;
		dexterity = 15;
		wisdom = 13;
		vitality = 12;
		maxMana = 100;
		maxHealth = 100;
		startTimer();

		maxAtk = 75;
		maxSpd = 50;
		maxDef = 30;
		maxVit = 50;
		maxWis = 75;
		maxDex = 75;
		maximumHealth = 800;
		maximumMana = 300;

		equipment.put("weapon", new T1Staff(this));
		equipment.put("ability", new T1Spell(this));
		equipment.put("armor", new T1Robe(this));
		equipment.put("ring", new T1HpRing(this));

		abilityCost = equipment.get("ability").getAbilityCost();
		mana = maxMana;
		health = maxHealth;
	}

	/**
	 * What happens when the key is pressed
	 *
	 */
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE && mana >= abilityCost) {
			Point2D mousePoint = MouseInfo.getPointerInfo().getLocation();
			mousePoint.setLocation(mousePoint.getX() - board.getJFrame().getX(),
					mousePoint.getY() - board.getJFrame().getY());
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 2 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 3 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 4 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 5 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 6 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 7 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 8 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 9 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 10 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 11 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			board.getPlayerProjectiles().add(new Projectile(mousePoint.getX(), mousePoint.getY(), 12 * ANGLE,
					((Item) equipment.get("ability")).getBulletImage(), 800, 8, equipment.get("ability").getDamage()));
			useSuper(abilityCost);
		}

	}

	/**
	 * Fire method
	 *
	 */
	public void fire(Point2D point) {
		board.getPlayerProjectiles().add(new Projectile(x, y, point.getX(), point.getY(),
				((Item) equipment.get("weapon")).getBulletImage(), 600, 7, attack * 2));
	}

}
