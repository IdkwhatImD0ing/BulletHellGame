package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

import java.lang.Math;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

import bags.Bag;
import enemies.*;
import equipment.Item;
import pots.Pot;
import sprite.Player;
import sprite.Projectile;
import sprite.Sprite;
import sprite.Wizard;

/**Main Game Loop class
 * @author Bill Zhang
 *
 */
public class Board extends JPanel implements ActionListener{

	private Timer timer;
	private Player player;
	private final int DELAY = 10;
	private Image backgroundImage;
	private int boardX;
	private int boardY;
	Random random = new Random();
	private int round;
	private JFrame jFrame;
	
	private List<Enemy> enemies;
	private boolean inGame;
	private List<Projectile> playerProjectiles;
	private List<Projectile> enemyProjectiles; 
	private List<Bag> bags;
	private Map<String, Integer> enemyMap;
	
	
	private Bag mainBag;
	
	/**Constructor
	 * @param x width of board
	 * @param y height
	 * @param frame frame the board is in
	 */
	public Board(int x, int y, JFrame frame) {
		round = 1;
		boardX = x;
		boardY = y;
		jFrame = frame;
		playerProjectiles = new ArrayList<>();
		enemyProjectiles = new ArrayList<>();
		enemyMap = new HashMap<>();
		enemyMap.put("ZombieKing", 0);
		enemyMap.put("Medusa", -1);
		mainBag = null;
		bags = new ArrayList<>();
		initBoard();
		backgroundImage = Toolkit.getDefaultToolkit().createImage("Map.png");
	}
	
	/**Initializes the board
	 * 
	 */
	private void initBoard() {
		setBounds(0, 0, 1620, 1080);
		addKeyListener(new TAdapter());
		addMouseListener(new MAdapter());
		
		setFocusable(true);
		inGame = true;
		
		player = new Wizard(boardX/2,boardY/2,this);

		initEnemies();
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	/**Initializes the enemies
	 * Also respawns them
	 * 
	 */
	private void initEnemies() {
		enemies = new ArrayList<>();
		
		for (int x = 0; x <= enemyMap.get("ZombieKing"); x++) {
			double number = random.nextDouble();
			if (number < 0.25) {
				enemies.add(new ZombieKing(0, (int)(boardY * random.nextDouble()), player, this));
				enemies.add(new ZombieMage(10, (int)(boardY * random.nextDouble()) + 10, player, this));
				enemies.add(new ZombieMage(10, (int)(boardY * random.nextDouble()) - 10, player, this));
				enemies.add(new ZombieArcher(-10, (int)(boardY * random.nextDouble()) + 10, player, this));
				enemies.add(new ZombieArcher(-10, (int)(boardY * random.nextDouble()) - 10, player, this));
				
			}
			else if (number < 0.5) {
				enemies.add(new ZombieKing((int)(boardX * random.nextDouble()), 0, player, this));
				enemies.add(new ZombieMage((int)(boardX * random.nextDouble()) + 10, 0, player, this));
				enemies.add(new ZombieMage((int)(boardX * random.nextDouble()) + 10, 0 + 10, player, this));
				enemies.add(new ZombieArcher((int)(boardX * random.nextDouble()) - 10, 0, player, this));
				enemies.add(new ZombieArcher((int)(boardX * random.nextDouble()) - 10, 0 + 10, player, this));
			}
			
			else if (number < 0.75) {
				enemies.add(new ZombieKing(boardX - 300, (int)(boardY * random.nextDouble()), player, this));
				enemies.add(new ZombieMage(boardX - 300 + 10, (int)(boardY * random.nextDouble()) - 10, player, this));
				enemies.add(new ZombieMage(boardX - 300 + 10, (int)(boardY * random.nextDouble()) + 10, player, this));
				enemies.add(new ZombieArcher(boardX - 300 - 10, (int)(boardY * random.nextDouble()) - 10, player, this));
				enemies.add(new ZombieArcher(boardX - 300 - 10, (int)(boardY * random.nextDouble()) + 10, player, this));
			}
			
			else if (number < 1) {
				enemies.add(new ZombieKing((int)((boardX - 300) * random.nextDouble()), boardY, player, this));
				enemies.add(new ZombieMage((int)((boardX - 300) * random.nextDouble()) + 10, boardY - 10, player, this));
				enemies.add(new ZombieMage((int)((boardX - 300) * random.nextDouble()) + 10, boardY + 10, player, this));
				enemies.add(new ZombieArcher((int)((boardX - 300) * random.nextDouble()) - 10, boardY - 10, player, this));
				enemies.add(new ZombieArcher((int)((boardX - 300) * random.nextDouble()) - 10, boardY + 10, player, this));
			}
		}
		
		if (round % 5 == 0 && round % 10 != 0) {
			for (int x = 0; x <= enemyMap.get("Medusa"); x++) {
				double number = random.nextDouble();
				if (number < 0.5) {
					enemies.add(new Medusa(0, (int)(boardY * random.nextDouble()), player, this));
					enemies.add(new Medusa((int)(boardX * random.nextDouble()), 0, player, this));
				}
				else {
					enemies.add(new Medusa(boardX, (int)(boardY * random.nextDouble()), player, this));
					enemies.add(new Medusa((int)(boardX * random.nextDouble()), boardY, player, this));
				}
				
			}
		}
		
		if (round == 10) {
			enemies.add(new Bes(810, 540, player, this));
		}
		else if (round == 20) {
			enemies.add(new Nut(810, 540, player, this));
		}
		else if (round == 30) {
			enemies.add(new Geb(810, 540, player, this));
		}
		else if (round == 40) {
			enemies.add(new Bes(810, 540, player, this));
			enemies.add(new Nut(810, 540, player, this));
		}
		else if (round == 50) {
			enemies.add(new Nut(810, 540, player, this));
			enemies.add(new Geb(810, 540, player, this));
		}
		else if (round == 60) {
			enemies.add(new Bes(810, 540, player, this));
			enemies.add(new Nut(810, 540, player, this));
			enemies.add(new Geb(810, 540, player, this));
			
		}
	}

	/**The paint component
	 * What to paint etc
	 *
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (inGame) {
			drawObjects(g);
			drawUi(g);
			checkMousePosition(g);
		}
		else {
			drawGameOver(g);
		}


		Toolkit.getDefaultToolkit().sync();
	}

	/**Draws the objects in the game
	 * @param g the graphics engine
	 */
	private void drawObjects(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(backgroundImage, 0, 0, this);
		if (player.isVisible()) {
			if (player.getLevelUp()) {
				Font font = new Font("Serial", Font.BOLD, 30);
				g2d.setFont(font);
				g2d.setColor(Color.GREEN);
				g2d.drawString("Level Up!", (int) player.getX() - 40, (int) player.getY() - 10);
			}
			if (player.getTakeDamage()) {
				Font font = new Font("Serial", Font.BOLD, 20);
				g2d.setFont(font);
				g2d.setColor(Color.RED);
				g2d.drawString("-" + player.getDamageTaken(), (int) player.getX() + 5 , (int) player.getY() - 10);
			}
			g2d.setColor(Color.black);
			g2d.drawImage(player.getImage(), (int)player.getX(), (int) player.getY(), this);
			g2d.setColor(Color.BLACK);
			g2d.drawRect((int)player.getX(), (int)player.getY() + player.getHeight() + 5, 42, 8);
			g2d.fillRect((int)player.getX() + 1, (int)player.getY() + 1 + player.getHeight() + 5, 41, 7);
			g2d.setColor(Color.GREEN);
			g2d.fillRect((int)player.getX() + 1, (int)player.getY() + 1 + player.getHeight() + 5, 
					(int)(((double)player.getHealth()/(double)player.getMaxHealth())*41), 7);
			
			
		}
		
		
		List<Projectile> playerProjectilesCopy = new ArrayList<>(playerProjectiles);
		List<Projectile> enemyProjectilesCopy = new ArrayList<>(enemyProjectiles);
		
		for (Projectile projectile : playerProjectilesCopy) {
			if (projectile.isVisible() && projectile != null) {
				g2d.drawImage(projectile.getImage(), (int) projectile.getX(),
					(int) projectile.getY(), this);
			}
		}
		
		for (Projectile projectile : enemyProjectilesCopy) {
			if (projectile.isVisible() && projectile != null) {
				g2d.drawImage(projectile.getImage(), (int) projectile.getX(),
					(int) projectile.getY(), this);
			}
		}
		
		for (Bag bag : bags) {
			if (bag.isVisible()) {
				g2d.drawImage(bag.getImage(), (int)bag.getX(),
						(int) bag.getY(), this);	
			}
		}
		
		for (Enemy enemy : enemies) {
			if (enemy.isVisible()) {
				g2d.drawImage(enemy.getImage(),(int) enemy.getX(), (int) enemy.getY(), this);
				g2d.setColor(Color.BLACK);
				g2d.drawRect((int)enemy.getX(), (int)enemy.getY() + enemy.getHeight() + 5, enemy.getWidth(), 8);
				g2d.fillRect((int)enemy.getX() + 1, (int)enemy.getY() + 1 + enemy.getHeight() + 5, enemy.getWidth()-1, 7);
				g2d.setColor(Color.GREEN);
				g2d.fillRect((int)enemy.getX() + 1, (int)enemy.getY() + 1 + enemy.getHeight() + 5, 
						(int)(((double)enemy.getHealth()/(double)enemy.getMaxHealth())*(enemy.getWidth()-1)), 7);
				if (enemy.getTakeDamage()) {
					Font font = new Font("Serial", Font.BOLD, 20);
					g2d.setFont(font);
					g2d.setColor(Color.RED);
					g2d.drawString("-" + enemy.getDamageTaken(), (int) enemy.getX() + 5 , (int) enemy.getY() - 10);
				}
			}
		}
		
		
		
		
		g2d.setColor(Color.BLACK);
		Font font = new Font("Serial", Font.BOLD, 20);
		
		g2d.setFont(font);
        g2d.drawString("Enemies left: " + enemies.size() + "  Round: " + round, 700, 20);
	}
	
	/**Draws the ui
	 * @param g the graphics engine
	 */
	private void drawUi(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.gray);
		g2d.fillRect(1620, 0, 300, 1080);
		g2d.setColor(Color.orange);
		
		g2d.fillRect(1640, 250, 
				(int)(((double)player.getExp()/(double)player.getMaxExp())*250), 30);
		g2d.setColor(Color.green);
		g2d.fillRect(1640, 300, 
				(int)(((double)player.getHealth()/(double)player.getMaxHealth())*250), 30);
		g2d.setColor(Color.blue);
		g2d.fillRect(1640, 350, 
				(int)(((double)player.getMana()/(double)player.getMaxMana())*250), 30);
		g2d.setColor(Color.black);
		g2d.drawRect(1620, 0, 300, 1080);
		g2d.drawRect(1640, 250, 250, 30);
		g2d.drawRect(1640, 300, 250, 30);
		g2d.drawRect(1640, 350, 250, 30);
		g2d.drawString(player.getExp() + " / " + player.getMaxExp(), 1720, 275);
		g2d.drawString("Level : " + player.getLevel(), 1700, 245);
		
		if (player.getMaxHealth() == player.getMaximumHealth()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		
		g2d.drawString(player.getHealth() + " / " + player.getMaxHealth(), 1720, 325);
		
		if (player.getMaxMana() == player.getMaximumMana()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		
		g2d.drawString(player.getMana() + " / " + player.getMaxMana(), 1720, 375);
		
		if (player.getAttack() == player.getMaxAtk()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Attack: " + player.getAttack(), 1640, 400);
		
		if (player.getDefense() == player.getMaxDef()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Defense " + player.getDefense(), 1760, 400);
		
		if (player.getSpeed() == player.getMaxSpd()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Speed: " + player.getSpeed(), 1640, 420);
		
		if (player.getDex() == player.getMaxDex()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Dexterity: " + player.getDex(), 1760, 420);
		
		if (player.getVit() == player.getMaxVit()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Vitality: " + player.getVit(), 1640, 440);
		
		if (player.getWis() == player.getMaxWis()) {
			g2d.setColor(Color.YELLOW);
		}
		else {
			g2d.setColor(Color.BLACK);
		}
		g2d.drawString("Wisdom: " + player.getWis(), 1760, 440);
		
		//Equipped items
		g2d.drawRect(1640, 500, 50, 50);
		g2d.drawRect(1690, 500, 50, 50);
		g2d.drawRect(1740, 500, 50, 50);
		g2d.drawRect(1790, 500, 50, 50);
		g2d.drawImage(((Item)player.getEquipment().get("weapon")).getImage(), 1640, 500, this);
		g2d.drawImage(((Item)player.getEquipment().get("ability")).getImage(), 1690, 500, this);
		g2d.drawImage(((Item)player.getEquipment().get("armor")).getImage(), 1740, 500, this);
		g2d.drawImage(((Item)player.getEquipment().get("ring")).getImage(), 1790, 500, this);
		
		//Inventory
		g2d.drawRect(1640, 600, 50, 50);
		g2d.drawRect(1690, 600, 50, 50);
		g2d.drawRect(1740, 600, 50, 50);
		g2d.drawRect(1790, 600, 50, 50);
		g2d.drawRect(1640, 650, 50, 50);
		g2d.drawRect(1690, 650, 50, 50);
		g2d.drawRect(1740, 650, 50, 50);
		g2d.drawRect(1790, 650, 50, 50);
		for (int x = 0; x < 4; x++)
		{
			if (player.inventory[x] != null) {
				g2d.drawImage(player.inventory[x].getImage(), 1640 + 50 * x, 600, this);
			}
		}
		
		for (int x = 4; x < 8; x++)
		{
			if (player.inventory[x]!= null) {
				g2d.drawImage(player.inventory[x].getImage(), 1640 + 50 * (x-4), 650, this);
			}
		}
		
		//Draw bag stuff
		if (mainBag != null) {
			g2d.drawRect(1640, 800, 50, 50);
			g2d.drawRect(1690, 800, 50, 50);
			g2d.drawRect(1740, 800, 50, 50);
			g2d.drawRect(1790, 800, 50, 50);
			g2d.drawRect(1640, 850, 50, 50);
			g2d.drawRect(1690, 850, 50, 50);
			g2d.drawRect(1740, 850, 50, 50);
			g2d.drawRect(1790, 850, 50, 50);
			for (int x = 0; x < 4; x++)
			{
				if (mainBag.getItem(x) != null) {
					g2d.drawImage(mainBag.getItem(x).getImage(), 1640 + (50 * x), 800, this);
				}
			}
			
			for (int x = 4; x < 8; x++)
			{
				if (mainBag.getItem(x) != null) {
					g2d.drawImage(mainBag.getItem(x).getImage(), 1640 + 50 * (x-4), 850, this);
				}
			}
		}
		
		
	}
	
	/**Checks the mouse position to see if it is hovering over the items
	 * @param g Graphics Engine
	 */
	public void checkMousePosition(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point2D e = MouseInfo.getPointerInfo().getLocation();
		e.setLocation(e.getX(), e.getY() - 25);
		Font font = new Font("Verdana", Font.BOLD, 15);
		g2d.setFont(font);
		
		if (e.getX() < 1640) {
			return;
		}
		
		else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 500 && e.getY() < 550) {
			if (player.getEquipment().get("weapon") != null) {
				g2d.drawImage(((Sprite) player.getEquipment().get("weapon")).getImage(), 1640, 25, this);
				if (((Sprite) player.getEquipment().get("weapon")).getInfo().length() > 30) {
					g2d.drawString(((Sprite) player.getEquipment().get("weapon")).getInfo().substring(0, ((Sprite) player.getEquipment().get("weapon")).getInfo().length() / 2), 1640, 100);
					g2d.drawString(((Sprite) player.getEquipment().get("weapon")).getInfo().substring(((Sprite) player.getEquipment().get("weapon")).getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(((Sprite) player.getEquipment().get("weapon")).getInfo(), 1640, 100);
			}
			
		}
		
		else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 500 && e.getY() < 550) {
			if (player.getEquipment().get("ability") != null) {
				g2d.drawImage(((Sprite) player.getEquipment().get("ability")).getImage(), 1640, 25, this);
				if (((Sprite) player.getEquipment().get("ability")).getInfo().length() > 30) {
					g2d.drawString(((Sprite) player.getEquipment().get("ability")).getInfo().substring(0, ((Sprite) player.getEquipment().get("ability")).getInfo().length() / 2), 1640, 100);
					g2d.drawString(((Sprite) player.getEquipment().get("ability")).getInfo().substring(((Sprite) player.getEquipment().get("ability")).getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(((Sprite) player.getEquipment().get("ability")).getInfo(), 1640, 100);
			}
			
		}

		else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 500 && e.getY() < 550) {
			if (player.getEquipment().get("armor") != null) {
				g2d.drawImage(((Sprite) player.getEquipment().get("armor")).getImage(), 1640, 25, this);
				if (((Sprite) player.getEquipment().get("armor")).getInfo().length() > 30) {
					g2d.drawString(((Sprite) player.getEquipment().get("armor")).getInfo().substring(0, ((Sprite) player.getEquipment().get("armor")).getInfo().length() / 2), 1640, 100);
					g2d.drawString(((Sprite) player.getEquipment().get("armor")).getInfo().substring(((Sprite) player.getEquipment().get("armor")).getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(((Sprite) player.getEquipment().get("armor")).getInfo(), 1640, 100);
			}
			
		}

		else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 500 && e.getY() < 550) {
			if (player.getEquipment().get("ring") != null) {
				g2d.drawImage(((Sprite) player.getEquipment().get("ring")).getImage(), 1640, 25, this);
				if (((Sprite) player.getEquipment().get("ring")).getInfo().length() > 30) {
					g2d.drawString(((Sprite) player.getEquipment().get("ring")).getInfo().substring(0, ((Sprite) player.getEquipment().get("ring")).getInfo().length() / 2), 1640, 100);
					g2d.drawString(((Sprite) player.getEquipment().get("ring")).getInfo().substring(((Sprite) player.getEquipment().get("ring")).getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(((Sprite) player.getEquipment().get("ring")).getInfo(), 1640, 100);
			}
			
		}

		
		else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 600 && e.getY() < 650) {
			if (player.inventory[0] != null) {
				g2d.drawImage(player.inventory[0].getImage(), 1640, 25, this);
				if (player.inventory[0].getInfo().length() > 30) {
					g2d.drawString(player.inventory[0].getInfo().substring(0, player.inventory[0].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[0].getInfo().substring(player.inventory[0].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[0].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 600 && e.getY() < 650) {
			if (player.inventory[1] != null) {
				g2d.drawImage(player.inventory[1].getImage(), 1640, 25, this);
				if (player.inventory[1].getInfo().length() > 30) {
					g2d.drawString(player.inventory[1].getInfo().substring(0, player.inventory[1].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[1].getInfo().substring(player.inventory[1].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[1].getInfo(), 1640, 100);
			}
		}
		else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 600 && e.getY() < 650) {
			if (player.inventory[2] != null) {
				g2d.drawImage(player.inventory[2].getImage(), 1640, 25, this);
				if (player.inventory[2].getInfo().length() > 30) {
					g2d.drawString(player.inventory[2].getInfo().substring(0, player.inventory[2].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[2].getInfo().substring(player.inventory[2].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[2].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 600 && e.getY() < 650) {
			if (player.inventory[3] != null) {
				g2d.drawImage(player.inventory[3].getImage(), 1640, 25, this);
				if (player.inventory[3].getInfo().length() > 30) {
					g2d.drawString(player.inventory[3].getInfo().substring(0, player.inventory[3].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[3].getInfo().substring(player.inventory[3].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[3].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1640 && e.getX() < 1690 && e.getY() > 650 && e.getY() < 700) {
			if (player.inventory[4] != null) {
				g2d.drawImage(player.inventory[4].getImage(), 1640, 25, this);
				if (player.inventory[4].getInfo().length() > 30) {
					g2d.drawString(player.inventory[4].getInfo().substring(0, player.inventory[4].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[4].getInfo().substring(player.inventory[4].getInfo().length() / 2), 1640, 200);
					return;
				}
				g2d.drawString(player.inventory[4].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1690 && e.getX() < 1740 && e.getY() > 650 && e.getY() < 700) {
			if (player.inventory[5] != null) {
				g2d.drawImage(player.inventory[5].getImage(), 1640, 25, this);
				if (player.inventory[5].getInfo().length() > 30) {
					g2d.drawString(player.inventory[5].getInfo().substring(0, player.inventory[5].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[5].getInfo().substring(player.inventory[5].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[5].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1740 && e.getX() < 1790 && e.getY() > 650 && e.getY() < 700) {
			if (player.inventory[6] != null) {
				g2d.drawImage(player.inventory[6].getImage(), 1640, 25, this);
				if (player.inventory[6].getInfo().length() > 30) {
					g2d.drawString(player.inventory[6].getInfo().substring(0, player.inventory[6].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[6].getInfo().substring(player.inventory[6].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[6].getInfo(), 1640, 100);
			}
			
		}
		else if (e.getX() > 1790 && e.getX() < 1840 && e.getY() > 650 && e.getY() < 700) {
			if (player.inventory[7] != null) {
				g2d.drawImage(player.inventory[7].getImage(), 1640, 25, this);
				if (player.inventory[7].getInfo().length() > 30) {
					g2d.drawString(player.inventory[7].getInfo().substring(0, player.inventory[7].getInfo().length() / 2), 1640, 100);
					g2d.drawString(player.inventory[7].getInfo().substring(player.inventory[7].getInfo().length() / 2), 1640, 150);
					return;
				}
				g2d.drawString(player.inventory[7].getInfo(), 1640, 100);
			}
			
		}
	}

	/**Draws the screen that shows the end screen
	 * @param g
	 */
	private void drawGameOver(Graphics g) {
		String msg = "Game Over \nYou survived: " + round + " rounds";
		Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.BLACK);
        g.setFont(small);
        g.drawString(msg, (boardX - fm.stringWidth(msg)) / 2,
                boardY / 2);
	}
	
	/**What happens if an action is performed
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			updateEnemies();
			updateProjectiles();
			updateBags();
			updateCharacter();
			checkCollisions();
			repaint();
		}
		
	}

	/**Updates the projectiles on screen
	 * 
	 */
	private void updateProjectiles() {



        for (int i = 0; i < playerProjectiles.size(); i++) {

            Projectile projectile = playerProjectiles.get(i);

            if (projectile != null && projectile.isVisible()) {

                projectile.move();
            } else {

                playerProjectiles.remove(i);
            }
        }
        
        
        for (int i = 0; i < enemyProjectiles.size(); i++) {

            Projectile projectile = enemyProjectiles.get(i);

            if (projectile != null && projectile.isVisible() ) {

                projectile.move();
                
            } else {

                enemyProjectiles.remove(i);
            }
        }
    }

	/**Updates the player
	 * 
	 */
	public void updateCharacter() {
		if (player.isVisible()) {
			player.move();
		}
	}
	
	/**Updates the bags on screen
	 * 
	 */
	public void updateBags() {
		for (int x = 0; x < bags.size(); x++) {
			if (!bags.get(x).isVisible()) {
				bags.remove(x);
			}
		}
	}
	
	/**Updates the enemies on screen
	 * 
	 */
	private void updateEnemies() {
		if (enemies.isEmpty()) {
			round++;
			if (round % 5 == 0) {
				enemyMap.put("Medusa", enemyMap.get("Medusa") + 1);
				enemyMap.put("ZombieKing", -1);
			}
			else {
				enemyMap.put("ZombieKing", enemyMap.get("ZombieKing") + 1);
			}
			
			initEnemies();
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.isVisible()) {
                enemy.move();
            } else {
                enemies.remove(i);
            }
		}
	}
	
	/**updates collisions
	 * 
	 */
	public void checkCollisions() {
		Rectangle playerBounds = player.getBounds();

		
		List<Projectile> playerProjectilesCopy = new ArrayList<>(playerProjectiles);
		List<Projectile> enemyProjectilesCopy = new ArrayList<>(enemyProjectiles);
		
		
		for (Projectile p : playerProjectilesCopy) {
			Rectangle projectileBound = p.getBounds();
			for (Enemy enemy : enemies) {
				Rectangle enemyBound = enemy.getBounds();
				if (projectileBound.intersects(enemyBound)) {
					p.setVisible(false);
					enemy.takeDamage(p.getBulletDamage());
					if (enemy.getHealth() <= 0) {
						enemy.die();
					}
				}
			}
		}
		
		for (Projectile p : enemyProjectilesCopy) {
			Rectangle projectileBound = p.getBounds();
			if (playerBounds.intersects(projectileBound)) {
				p.setVisible(false);
				if (player.getHealth()>p.getBulletDamage()) {
					player.takeDamage(p.getBulletDamage());
				}
				else {
					player.setVisible(false);
					inGame = false;
				}
				
				
				
			}
		}
		
		
		for (Bag bag : bags) {
			Rectangle bagBound = bag.getBounds();
			if (playerBounds.intersects(bagBound)) {
				mainBag = bag;
				return;
			}
		}
		mainBag = null;
		
		
		
	}
	
	/**New class for Keyadaptor. For key events.
	 * @author Bill
	 *
	 */
	private class TAdapter extends KeyAdapter {

		/**What happens when key is released
		 *
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		/**What happens when key is pressed
		 *
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
	}
	
	/**New class for mouseadaptor. for mouse events
	 * @author Bill
	 *
	 */
	private class MAdapter extends MouseAdapter{
		/**What happens when mouse is pressed
		 *
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			player.mousePressed(e);
		}
		
		/**What happens when mouse is released
		 *
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			player.mouseReleased(e);
		}
		
		
	}
	
	/**Gets the jframe
	 * @return jframe
	 */
	public JFrame getJFrame(){
		return jFrame;
	}

	/**Gets the player
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	
	/**Gets the list of player projectiles
	 * @return projectile list
	 */
	public List<Projectile> getPlayerProjectiles() {
        return playerProjectiles;
    }
	
	/**Gets list of enemy projectiles
	 * @return list of enemy projectiles
	 */
	public List<Projectile> getEnemyProjectiles(){ 
		return enemyProjectiles;
	}
	
	/**Gets list of bags
	 * @return bag list
	 */
	public List<Bag> getBags(){
		return bags;
	}
	
	/**Gets the main bag
	 * @return the mainbag
	 */
	public Bag getMainBag() {
		return mainBag;
	}
	
	/**Gets the round number
	 * @return round number
	 */
	public int getRound() {
		return round;
	}
	
	/**Get enemies list
	 * @return listof enemies
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
}
