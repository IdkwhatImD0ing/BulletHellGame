package sprite;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import bags.Bag;
import bags.BrownBag;
import equipment.Item;
import game.Board;
import game.canFire;
import game.fireChanger;
import game.regenStats;
import pots.HealthPot;
import pots.MpPot;
import pots.Pot;
import status.Status;

/**
 * Base class for players
 * 
 * @author Bill Zhang
 *
 */
public class Player extends Sprite {

	private boolean moveRight = false;
	private boolean moveLeft = false;
	private boolean moveUp = false;
	private boolean moveDown = false;

	private static final int MAX_LEVEL = 20;
	private int level = 1;

	protected int health;

	protected int mana;
	protected int exp = 0;
	protected int attack;
	protected int defense;
	protected int speed;
	protected int dexterity;
	protected int wisdom;
	protected int vitality;

	protected int maxAtk;
	protected int maxDef;
	protected int maxSpd;
	protected int maxDex;
	protected int maxVit;
	protected int maxWis;
	protected int maxMana;
	protected int maxHealth;
	protected int maximumHealth;
	protected int maximumMana;
	protected int maxExp = 100;

	private int healthPots;
	private int manaPots;

	private boolean fire;
	private Timer fireTimer;
	private Timer fireChanger;
	private Timer regenTimer;
	TimerTask fireThread;

	private String rightImage;
	private String leftImage;
	private String upImage;
	private String downImage;
	private String movingRightImage;
	private String movingLeftImage;
	private String movingUpImage;
	private String movingDownImage;

	private Timer levelUpTimer;
	private boolean levelUp;

	private int takeDamageInt;
	public Map<String, Item> equipment;
	public Sprite[] inventory;

	private ArrayList<Damage> damageList;
	//private ArrayList<Status> statusEffects;
	private Timer timer;

	/**
	 * Constructor
	 * 
	 * @param x           x position
	 * @param y           y position
	 * @param board       board player is on
	 * @param rightImage  sprite // * @param leftImage sprite
	 * @param downImage   sprite
	 * @param upImage     sprite
	 * @param movingRight sprite
	 * @param movingLeft  sprite
	 * @param movingUp    sprite
	 * @param movingDown  sprite
	 */
	public Player(int x, int y, Board board, String rightImage, String leftImage, String downImage, String upImage,
			String movingRight, String movingLeft, String movingUp, String movingDown) {
		super(x, y, board);
		levelUpTimer = new Timer();
		fire = true;

		this.rightImage = rightImage;
		this.leftImage = leftImage;
		this.downImage = downImage;
		this.upImage = upImage;
		healthPots = 0;
		manaPots = 0;
		equipment = new HashMap<String, Item>(4);
		movingRightImage = movingRight;
		movingLeftImage = movingLeft;
		movingUpImage = movingUp;
		movingDownImage = movingDown;
		damageList = new ArrayList<Damage>();
		//statusEffects = new ArrayList<Status>();
		initPlayer();
		fireChanger = new Timer();
		fireChanger.schedule(new fireChanger(this, board), 0, 400 - (dexterity * 4));
		inventory = new Sprite[] { null, null, null, null, null, null, null, null };

	}

	/**
	 * Initializes the player sprite
	 * 
	 */
	private void initPlayer() {

		loadImage(rightImage);
		getImageDimensions();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (damageList.size() == 0) {
					return;
				}
				for (int i = 0; i < damageList.size(); i++) {
					if (damageList.get(i).time > 8) {
						damageList.remove(i);
					} else {
						damageList.get(i).time += 1;
					}
				}
			}
		}, 0, 10);
	}

	/**
	 * Move function
	 * 
	 */
	public void move() {
		if (exp >= maxExp) {
			levelUp();
		}

		if (moveRight && moveLeft) {

		} else if (moveRight) {
			loadImage(movingRightImage);
			if (getX() < 1620 - getWidth()) {
				x += 1.5 + ((double) speed) / 20.0;
			}

		} else if (moveLeft) {
			loadImage(movingLeftImage);
			if (getX() > 0) {
				x -= 1.5 + ((double) speed) / 20.0;
			}

		}

		if (moveUp && moveDown) {

		} else if (moveUp) {
			if (moveLeft) {
				loadImage(movingLeftImage);
			} else if (moveRight) {
				loadImage(movingRightImage);
			} else {
				loadImage(movingUpImage);
			}
			if (getY() > 0) {
				y -= 1.5 + ((double) speed) / 20.0;
			}
		} else if (moveDown) {
			if (moveLeft) {
				loadImage(movingLeftImage);
			} else if (moveRight) {
				loadImage(movingRightImage);
			} else {
				loadImage(movingDownImage);
			}
			if (getY() < 1080 - getHeight()) {
				y += 1.5 + ((double) speed) / 20.0;
			}

		}
	}

	/**
	 * Gets x position
	 *
	 */
	public double getX() {

		return x;
	}

	/**
	 * get y position
	 *
	 */
	public double getY() {

		return y;
	}

	/**
	 * get width
	 *
	 */
	public int getWidth() {

		return width;
	}

	/**
	 * get height
	 *
	 */
	public int getHeight() {

		return height;
	}

	/**
	 * get image
	 *
	 */
	public Image getImage() {

		return image;
	}

	/**
	 * key press event
	 * 
	 * @param e the keyevent
	 */
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			moveLeft = true;
		}

		if (key == KeyEvent.VK_D) {
			moveRight = true;
		}

		if (key == KeyEvent.VK_W) {
			moveUp = true;
		}

		if (key == KeyEvent.VK_S) {
			moveDown = true;
		}

	}

	/**
	 * mouse press event
	 * 
	 * @param e mouse that was pressed
	 */
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		if (button == MouseEvent.BUTTON1) {
			if (e.getX() < 1620) {
				if (getFire()) {
					fireChanger.cancel();
					fireChanger = new Timer();
					fireChanger.schedule(new fireChanger(this, board), 400 - (dexterity * 4), 400 - (dexterity * 4));
				}
				fireTimer = new Timer();
				fireTimer.schedule(new canFire(this, board), 0, 10);

			}

			else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 600 && e.getY() < 650) {
				if (inventory[0] == null) {
					return;
				} else if (inventory[0] instanceof Pot) {
					inventory[0].usePot(this);
					inventory[0] = null;
				} else if (inventory[0] instanceof Item) {
					Item thisItem = (Item) inventory[0];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[0] = tempItem;
				}
			} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 600 && e.getY() < 650) {
				if (inventory[1] == null) {
					return;
				} else if (inventory[1] instanceof Pot) {
					inventory[1].usePot(this);
					inventory[1] = null;
				} else if (inventory[1] instanceof Item) {
					Item thisItem = (Item) inventory[1];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[1] = tempItem;
				}
			} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 600 && e.getY() < 650) {
				if (inventory[2] == null) {
					return;
				} else if (inventory[2] instanceof Pot) {
					inventory[2].usePot(this);
					inventory[2] = null;
				} else if (inventory[2] instanceof Item) {
					Item thisItem = (Item) inventory[2];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[2] = tempItem;
				}
			} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 600 && e.getY() < 650) {
				if (inventory[3] == null) {
					return;
				} else if (inventory[3] instanceof Pot) {
					inventory[3].usePot(this);
					inventory[3] = null;
				} else if (inventory[3] instanceof Item) {
					Item thisItem = (Item) inventory[3];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[3] = tempItem;
				}
			} else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 650 && e.getY() < 700) {
				if (inventory[4] == null) {
					return;
				} else if (inventory[4] instanceof Pot) {
					inventory[4].usePot(this);
					inventory[4] = null;
				} else if (inventory[4] instanceof Item) {
					Item thisItem = (Item) inventory[4];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[4] = tempItem;
				}
			} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 650 && e.getY() < 700) {
				if (inventory[5] == null) {
					return;
				} else if (inventory[5] instanceof Pot) {
					inventory[5].usePot(this);
					inventory[5] = null;
				} else if (inventory[5] instanceof Item) {
					Item thisItem = (Item) inventory[5];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[5] = tempItem;
				}
			} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 650 && e.getY() < 700) {
				if (inventory[6] == null) {
					return;
				} else if (inventory[6] instanceof Pot) {
					inventory[6].usePot(this);
					inventory[6] = null;
				} else if (inventory[6] instanceof Item) {
					Item thisItem = (Item) inventory[6];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[6] = tempItem;
				}
			} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 650 && e.getY() < 700) {
				if (inventory[7] == null) {
					return;
				} else if (inventory[7] instanceof Pot) {
					inventory[7].usePot(this);
					inventory[7] = null;
				} else if (inventory[7] instanceof Item) {
					Item thisItem = (Item) inventory[7];
					Item tempItem = equipment.get(thisItem.getType());
					tempItem.unEquipItem();
					thisItem.equipItem();
					equipment.put(thisItem.getType(), thisItem);
					inventory[7] = tempItem;
				}
			}

			else if (board.getMainBag() != null) {
				Bag bag = board.getMainBag();
				if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 800 && e.getY() < 850) {
					if (bag.getItem(0) == null) {
						return;
					} else {
						if (bag.getItem(0) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(0) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(0));
						}
						bag.removeItem(0);
					}

				} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 800 && e.getY() < 850) {
					if (bag.getItem(1) == null) {
						return;
					} else {
						if (bag.getItem(1) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(1) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(1));
						}
						bag.removeItem(1);
					}
				} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 800 && e.getY() < 850) {
					if (bag.getItem(2) == null) {
						return;
					} else {
						if (bag.getItem(2) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(2) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(2));
						}
						bag.removeItem(2);
					}
				} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 800 && e.getY() < 850) {
					if (bag.getItem(3) == null) {
						return;
					} else {
						if (bag.getItem(3) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(3) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(3));
						}
						bag.removeItem(3);
					}
				} else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 850 && e.getY() < 900) {
					if (bag.getItem(4) instanceof HealthPot && healthPots <= 6) {
						healthPots++;
					} else if (bag.getItem(4) instanceof MpPot && manaPots <= 6) {
						manaPots++;
					} else {
						addToInventory(bag.getItem(4));
					}
					bag.removeItem(4);
				} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 850 && e.getY() < 900) {
					if (bag.getItem(5) == null) {
						return;
					} else {
						if (bag.getItem(5) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(5) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(5));
						}
						bag.removeItem(5);
					}
				} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 850 && e.getY() < 900) {
					if (bag.getItem(6) == null) {
						return;
					} else {
						if (bag.getItem(6) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(6) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(6));
						}
						bag.removeItem(6);
					}
				} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 850 && e.getY() < 900) {
					if (bag.getItem(7) == null) {
						return;
					} else {
						if (bag.getItem(7) instanceof HealthPot && healthPots <= 6) {
							healthPots++;
						} else if (bag.getItem(7) instanceof MpPot && manaPots <= 6) {
							manaPots++;
						} else {
							addToInventory(bag.getItem(7));
						}
						bag.removeItem(7);
					}
				}
			}
		} else if (button == MouseEvent.BUTTON3) {
			Bag mainBag = board.getMainBag();
			if (e.getX() < 1620) {
				return;
			}

			else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 600 && e.getY() < 650 && inventory[0] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[0]);
					inventory[0] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[0]);
					board.getBags().add(drop);
					inventory[0] = null;

				}
			} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 600 && e.getY() < 650 && inventory[1] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[1]);
					inventory[1] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[1]);
					board.getBags().add(drop);
					inventory[1] = null;

				}
			} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 600 && e.getY() < 650 && inventory[2] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[2]);
					inventory[2] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[2]);
					board.getBags().add(drop);
					inventory[2] = null;

				}
			} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 600 && e.getY() < 650 && inventory[3] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[3]);
					inventory[3] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[3]);
					board.getBags().add(drop);
					inventory[3] = null;

				}
			} else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 650 && e.getY() < 700 && inventory[4] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[4]);
					inventory[4] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[4]);
					board.getBags().add(drop);
					inventory[4] = null;

				}
			} else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 650 && e.getY() < 700 && inventory[5] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[5]);
					inventory[5] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[5]);
					board.getBags().add(drop);
					inventory[5] = null;

				}
			} else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 650 && e.getY() < 700 && inventory[6] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[6]);
					inventory[6] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[6]);
					board.getBags().add(drop);
					inventory[6] = null;

				}
			} else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 650 && e.getY() < 700 && inventory[7] != null) {
				if (mainBag != null) {
					mainBag.add(inventory[7]);
					inventory[7] = null;
				} else {
					Bag drop = new BrownBag(x, y, board);
					drop.add(inventory[7]);
					board.getBags().add(drop);
					inventory[7] = null;

				}
			}
		}
	}

	/**
	 * Mouse release event
	 * 
	 * @param e
	 */
	public void mouseReleased(MouseEvent e) {
		if (fireTimer != null) {
			fireTimer.cancel();
		}
	}

	/**
	 * Fire method
	 * 
	 * @param point
	 */
	public void fire(Point2D point) {
		return;
	}

	/**
	 * Key release event
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			if (!moveRight) {
				loadImage(leftImage);
			}
			moveLeft = false;
		}

		if (key == KeyEvent.VK_D) {
			if (!moveLeft) {
				loadImage(rightImage);
			}
			moveRight = false;
		}

		if (key == KeyEvent.VK_W) {
			if (!moveDown) {
				loadImage(upImage);
			}
			moveUp = false;
		}

		if (key == KeyEvent.VK_S) {
			if (!moveUp) {
				loadImage(downImage);
			}
			moveDown = false;
		}

		if (key == KeyEvent.VK_F && healthPots > 0) {
			healthPots--;
			increaseHealth(100);
		}

		if (key == KeyEvent.VK_V && manaPots > 0) {
			manaPots--;
			increaseMana(100);
		}
	}

	/**
	 * @param damage
	 */
	public void takeDamage(int damage) {
		int minimumDamage = (int) (0.2 * damage);

		if (damage <= defense) {
			health -= minimumDamage;
			takeDamageInt = minimumDamage;
		} else if ((double) (damage - defense) / (double) damage < 0.2) {
			health -= minimumDamage;
			takeDamageInt = minimumDamage;
		} else {
			health = health - (damage - defense);
			takeDamageInt = damage;
		}

		Damage newDamage = new Damage(takeDamageInt, 0);
		damageList.add(newDamage);

	}

	/**
	 * use super method
	 * 
	 * @param cost ability cost of super
	 */
	public void useSuper(int cost) {
		mana = mana - cost;
	}

	/**
	 * Regens the hp according to vit
	 * 
	 */
	public void regenHp() {
		double heal = vitality * 0.15;
		if (health + heal > maxHealth) {
			health = maxHealth;
		} else {
			health += heal;
		}
	}

	/**
	 * Regens the Mana according to Wis
	 * 
	 */
	public void regenMana() {
		double manaRegen = wisdom * 0.2;
		if (mana + manaRegen > maxMana) {
			mana = maxMana;
		} else {
			mana += (int) manaRegen;
		}
	}

	/**
	 * Start timer for regenning hp and mana
	 * 
	 */
	public void startTimer() {
		regenTimer = new Timer();
		regenTimer.schedule(new regenStats(this), 0, 500);
	}

	// Getters for all attributes
	/**
	 * Gets health
	 * 
	 * @return health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gets level
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Get max health
	 * 
	 * @return max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Gets the max mana
	 * 
	 * @return max mana
	 */
	public int getMaxMana() {
		return maxMana;
	}

	/**
	 * Gets mana
	 * 
	 * @return mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * Gets speed
	 * 
	 * @return speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Gets the attack
	 * 
	 * @return attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Gets defense
	 * 
	 * @return defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * Get the dex
	 * 
	 * @return dex
	 */
	public int getDex() {
		return dexterity;
	}

	/**
	 * Get vit
	 * 
	 * @return vit
	 */
	public int getVit() {
		return vitality;
	}

	/**
	 * Get wis
	 * 
	 * @return wis
	 */
	public int getWis() {
		return wisdom;
	}

	/**
	 * Returns the health pots of the player
	 * 
	 * @return Health pots
	 */
	public int getHealthPots() {
		return healthPots;
	}

	/**
	 * Returns the mana pots of the player
	 * 
	 * @return Mana pots
	 */
	public int getManaPots() {
		return manaPots;
	}

	/**
	 * Get max attack
	 * 
	 * @return max attack
	 * 
	 */
	public int getMaxAtk() {
		return maxAtk;
	}

	/**
	 * Get max defense
	 * 
	 * @return max defense
	 */
	public int getMaxDef() {
		return maxDef;
	}

	/**
	 * Get max vit
	 * 
	 * @return max vit
	 */
	public int getMaxVit() {
		return maxVit;
	}

	/**
	 * Get max dex
	 * 
	 * @return max dex
	 */
	public int getMaxDex() {
		return maxDex;
	}

	/**
	 * Get max spd
	 * 
	 * @return max spd
	 */
	public int getMaxSpd() {
		return maxSpd;
	}

	/**
	 * Get max wis
	 * 
	 * @return max wis
	 */
	public int getMaxWis() {
		return maxWis;
	}

	/**
	 * Get maximum mana
	 * 
	 * @return maximum mana
	 */
	public int getMaximumMana() {
		return maximumMana;
	}

	/**
	 * Get maximum health
	 * 
	 * @return maximum health
	 */
	public int getMaximumHealth() {
		return maximumHealth;
	}

	/**
	 * Get map of equipment
	 * 
	 * @return the equipment max
	 */
	public Map<String, Item> getEquipment() {
		return equipment;
	}

	/**
	 * Increases the def
	 * 
	 * @param x Amount of def to increase
	 */
	public void increaseDef(int x) {
		defense += x;
	}

	/**
	 * Increases the atk
	 * 
	 * @param x amount of attack to increase
	 */
	public void increaseAtk(int x) {
		attack += x;
	}

	/**
	 * Increase speed
	 * 
	 * @param x amount of speed to increase
	 */
	public void increaseSpd(int x) {
		speed += x;
	}

	/**
	 * Increase vit
	 * 
	 * @param x amount of vit to increase
	 */
	public void increaseVit(int x) {
		vitality += x;
	}

	/**
	 * Increase wis
	 * 
	 * @param x amount of wis to increase
	 */
	public void increaseWis(int x) {
		wisdom += x;
	}

	/**
	 * Increase dex
	 * 
	 * @param x amount of dex to increase
	 */
	public void increaseDex(int x) {
		dexterity += x;
		fireChanger.cancel();
		fireChanger = new Timer();
		fireChanger.schedule(new fireChanger(this, board), 0, 400 - (dexterity * 4));
	}

	/**
	 * Increase health
	 * 
	 * @param x amount of health to increase by
	 * 
	 */
	public void increaseHealth(int x) {
		if (health + x > maxHealth) {
			health = maxHealth;
			return;
		}
		health += x;
	}

	/**
	 * Increase mana
	 * 
	 * @param x amount of mana to increase
	 */
	public void increaseMana(int x) {
		if (mana + x > maxMana) {
			mana = maxMana;
			return;
		}
		mana += x;
	}

	/**
	 * Increase max health
	 * 
	 * @param x amount to increase max health by
	 */
	public void increaseMaxHealth(int x) {
		maxHealth += x;
	}

	/**
	 * Increase max mana
	 * 
	 * @param x amount of max mana to increase by
	 */
	public void increaseMaxMana(int x) {
		maxMana += x;
	}

	/**
	 * Increase max speed
	 * 
	 * @param x max speed to increase by
	 */
	public void increaseMaxSpd(int x) {
		maxSpd += x;
	}

	/**
	 * Increase max def
	 * 
	 * @param x def to increas by
	 */
	public void increaseMaxDef(int x) {
		maxDef += x;
	}

	/**
	 * Increase max dex
	 * 
	 * @param x amount to increase max dex
	 */
	public void increaseMaxDex(int x) {
		maxDex += x;
	}

	/**
	 * Increases max vit
	 * 
	 * @param x amount of vit to increase
	 * 
	 */
	public void increaseMaxVit(int x) {
		maxVit += x;
	}

	/**
	 * Increase max wis
	 * 
	 * @param x amount of max wis to increase
	 */
	public void increaseMaxWis(int x) {
		maxWis += x;
	}

	/**
	 * INcrease max attack
	 * 
	 * @param x amount of max attack to increase
	 */
	public void increaseMaxAtk(int x) {
		maxAtk += x;
	}

	/**
	 * Increase maximum health
	 * 
	 * @param x increase max health by
	 */
	public void increaseMaximumHealth(int x) {
		maximumHealth += x;
	}

	/**
	 * Increase max mana
	 * 
	 * @param x amount
	 */
	public void increaseMaximumMana(int x) {
		maximumMana += x;
	}

	// Level Up Methods
	/**
	 * Gets the exp
	 * 
	 * @return exp of player
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * gets max xp
	 * 
	 * @return max xp of player
	 */
	public int getMaxExp() {
		return maxExp;
	}

	/**
	 * Gains xp
	 * 
	 * @param exp amount of xp to gain
	 */
	public void gainExp(int exp) {
		if (level < MAX_LEVEL) {
			this.exp += exp;
		}
	}

	/**
	 * Level up method
	 * 
	 */
	public void levelUp() {
		if (level < MAX_LEVEL) {
			levelUp = true;
			levelUpTimer = new Timer();
			levelUpTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					levelUp = false;
					levelUpTimer.cancel();

				}
			}, 1000);
			level++;
			exp = 0;
			maxExp += 100;
			if (attack < maxAtk) {
				increaseAtk(2);
			}
			if (defense < maxDef) {
				increaseDef(1);

			}
			if (dexterity < maxDex) {
				increaseDex(2);

			}

			if (speed < maxSpd) {
				increaseSpd(1);

			}
			if (vitality < maxVit) {
				increaseVit(1);

			}
			if (wisdom < maxWis) {
				increaseWis(2);

			}
			if (maxMana < maximumMana) {
				increaseMaxHealth(10);

			}
			if (maxHealth < maximumHealth) {
				increaseMaxMana(15);

			}
			maxHealth();
			maxMana();
		}

	}

	/**
	 * Maximises the health
	 * 
	 */
	public void maxHealth() {
		health = maxHealth;
	}

	/**
	 * Refills mana
	 * 
	 */
	public void maxMana() {
		mana = maxMana;
	}

	/**
	 * Adds item to inventory
	 * 
	 * @param x sprite to add
	 * @return 1 if success ful -1 if not
	 */
	public int addToInventory(Sprite x) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = x;
				return 1;
			}
		}
		return -1;
	}

	/**
	 * Checks if character is leveling up
	 * 
	 * @return yes or no
	 * 
	 */
	public boolean getLevelUp() {
		return levelUp;
	}

	/**
	 * Returns the damage the player took
	 * 
	 * @return Damage from attack
	 */
	public int getDamageTaken() {
		return takeDamageInt;
	}

	public ArrayList<Damage> getDamageList() {
		return damageList;
	}

	public void setFire(boolean bool) {
		fire = bool;
	}

	public boolean getFire() {
		return fire;
	}

}
