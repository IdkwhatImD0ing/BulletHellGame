package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Title Screen Class
 * 
 * @author Bill Zhang
 *
 */
public class TitleScreen extends JPanel implements ActionListener {

	private Application jFrame;
	private JButton startButton;
	private JButton exitButton;

	/**
	 * Constructor
	 * 
	 * @param x     x length
	 * @param y     y length
	 * @param frame frame this component is in
	 */
	public TitleScreen(int x, int y, Application frame) {
		jFrame = frame;
		initScreen();

	}

	/**
	 * initializes the screen
	 * 
	 */
	public void initScreen() {
		setBounds(0, 0, 1920, 1080);
		setFocusable(true);
		Color backColor = new Color(51, 102, 0);
		setBackground(backColor);
		startButton = new JButton("Start");
		exitButton = new JButton("Exit");
		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		add(startButton);
		add(exitButton);

	}

	/**
	 * Paints the Gui
	 *
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawUi(g);
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * The stuff that is painted
	 * 
	 * @param g The graphics class we are using.
	 */
	public void drawUi(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.drawRect(100, 100, 300, 880);
		g2d.drawRect(1520, 100, 300, 880);
		Font font = new Font("Serif", Font.PLAIN, 30);
		g2d.setFont(font);
		g2d.drawString("Controls", 200, 150);
		g2d.drawString("Tips", 1640, 150);
		font = new Font("Serif", Font.PLAIN, 20);

		g2d.setFont(font);

		g2d.drawString("W = Move up", 130, 200);
		g2d.drawString("A = Move left", 130, 250);
		g2d.drawString("S = Move down", 130, 300);
		g2d.drawString("D = Move right", 130, 350);
		g2d.drawString("Right Click = Use item / shoot", 130, 400);
		g2d.drawString("Left Click = Drop item", 130, 450);
		g2d.drawString("Atk increases damage dealt", 130, 650);
		g2d.drawString("Def decreases damage taken", 130, 600);
		g2d.drawString("Speed affects movement", 130, 700);
		g2d.drawString("Dex increases firing rate", 130, 550);
		g2d.drawString("Vit increase hp regen", 130, 750);
		g2d.drawString("Wis increases mana regen", 130, 800);

		g2d.drawString("Blue Bags contains pots", 1550, 200);
		g2d.drawString("Pots permanently increase stats", 1550, 225);
		g2d.drawString("White Bags contains rare items", 1550, 300);
		g2d.drawString("Recommended to get them", 1550, 325);
		g2d.drawString("Bags despawn after 10 seconds", 1550, 400);
		g2d.drawString("Get them quick", 1550, 425);
		g2d.drawString("Your stats are on the right", 1550, 500);
		g2d.drawString("Pay close attention to ", 1550, 550);
		g2d.drawString("your health and mana", 1550, 575);
		g2d.drawString("When your health drops to 0,", 1550, 625);
		g2d.drawString("you lose!", 1550, 650);

		g2d.drawString("Descriptions of items", 1550, 700);
		g2d.drawString("are in the top right corner", 1550, 725);

		startButton.setSize(620, 200);
		startButton.setLocation(650, 480);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setFont(font);
		startButton.setForeground(Color.WHITE);

		exitButton.setSize(620, 200);
		exitButton.setLocation(650, 780);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFont(font);
		exitButton.setForeground(Color.WHITE);

		font = new Font("Serif", Font.BOLD, 250);
		g2d.setFont(font);
		g2d.drawString("EXALT", 530, 350);

	}

	/**
	 * Actionlistener for the buttons
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			jFrame.initGame();
		} else if (e.getSource() == exitButton) {
			jFrame.exit();
		}
	}

}
