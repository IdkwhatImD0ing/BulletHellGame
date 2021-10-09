	package game;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**Frame of the game that Extends JFrame
 * @author Bill Zhang
 *
 */
public class Application extends JFrame {
	
	private int dimensionX = 1920;
	private int dimensionY = 1080;
	public TitleScreen menu;
	public Board game;
	
	/** Constructor
	 * 
	 */
	public Application() {

		initUI();
	}

	/** Initializes the Title Screen
	 * 
	 */
	private void initUI() {
		menu = new TitleScreen(dimensionX, dimensionY, this);
		add(menu);
		//add(new Board(dimensionX, dimensionY, this));
		setLocation(0, 0);
		setSize(dimensionX, dimensionY);
		setResizable(false);
		setTitle("Title Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		

		
	}
	
	/**Starts the Main Game
	 * 
	 */
	public void initGame() {
		menu.setVisible(false);
		game = new Board(dimensionX, dimensionY, this);
		add(game);
		game.grabFocus();
		setLocation(0, 0);
		setSize(dimensionX, dimensionY);
		setResizable(false);
		setTitle("Title Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	


	/**Exits the program
	 * 
	 */
	public void exit() {
		System.exit(0);
	}
	
	/**Main loop 
	 * @param args arguments
	 */
	public static void main(String[] args) {

		
		
		
		EventQueue.invokeLater(() -> {
			Application ex = new Application();
			ex.setVisible(true);
			
		});
	}
}
