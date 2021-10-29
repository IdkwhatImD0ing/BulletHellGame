package equipment;

import java.awt.Image;

import sprite.Player;
import sprite.Sprite;

/**
 * Main class for items Other extended classes have no javadoc because they are
 * just an item with different stats.
 * 
 * @author aaaab
 *
 */
public class Item extends Sprite {

	protected String type;
	protected boolean equipped;
	protected int attack;
	protected int speed;
	protected int wisdom;
	protected int vitality;
	protected int dexterity;
	protected int mana;
	protected int health;
	protected int defense;
	private Player player;
	protected Image bulletImage;

	/**
	 * Constructor
	 * 
	 * @param player
	 * @param imageString
	 * @param type
	 * @param equipped
	 */
	public Item(Player player, String imageString, String type, boolean equipped) {
		super(0, 0, player.getBoard());
		this.board = player.getBoard();
		this.equipped = equipped;
		this.type = type;
		this.player = player;
		loadImage(imageString);
		attack = 0;
		speed = 0;
		wisdom = 0;
		vitality = 0;
		dexterity = 0;
		mana = 0;
		health = 0;
		defense = 0;
	}

	/**
	 * Equips the item and modifies the stats of player to match
	 *
	 */
	public void equipItem() {
		player.increaseAtk(attack);
		player.increaseMaxHealth(health);
		player.increaseDef(defense);
		player.increaseDex(dexterity);
		player.increaseMaxMana(mana);
		player.increaseSpd(speed);
		player.increaseVit(vitality);
		player.increaseWis(wisdom);
		player.increaseMaxAtk(attack);
		player.increaseMaximumHealth(health);
		player.increaseMaximumMana(mana);
		player.increaseMaxDef(defense);
		player.increaseMaxSpd(speed);
		player.increaseMaxDex(dexterity);
		player.increaseMaxWis(wisdom);
		player.increaseMaxVit(vitality);
		equipped = true;
	}

	/**
	 * Unequip Item and modifies the stats of the player
	 * 
	 */
	public void unEquipItem() {
		player.increaseMaxAtk(-attack);
		player.increaseMaximumHealth(-health);
		player.increaseMaximumMana(-mana);
		player.increaseMaxDef(-defense);
		player.increaseMaxSpd(-speed);
		player.increaseMaxDex(-dexterity);
		player.increaseMaxWis(-wisdom);
		player.increaseMaxVit(-vitality);
		player.increaseAtk(-attack);
		player.increaseMaxHealth(-health);
		player.increaseDef(-defense);
		player.increaseDex(-dexterity);
		player.increaseMaxMana(-mana);
		player.increaseSpd(-speed);
		player.increaseVit(-vitality);
		player.increaseWis(-wisdom);
		equipped = false;
	}

	/**
	 * Gets the type of the item
	 *
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the damage of the item
	 * 
	 * @return attack
	 */
	public int getDamage() {
		return attack;
	}

	/**
	 * Gets the ability cost of specific items
	 * 
	 * @return ability cost
	 */
	public int getAbilityCost() {
		return 0;
	}

	/**
	 * Gets the description of the item
	 * 
	 * @return the description
	 */
	public String getInfo() {
		return "";
	}

	/**
	 * Gets the image of the projectile fired by staff items
	 * 
	 * @return image
	 */
	public Image getBulletImage() {
		return bulletImage;
	}

}
