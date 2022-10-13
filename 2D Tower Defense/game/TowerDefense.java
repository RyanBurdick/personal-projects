package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * A TowerDefense panel is a GUI panel that displays a tower
 * defense path on the screen, and animates small objects
 * moving along the path.
 * 
 * @author R. Burdick
 */
public class TowerDefense extends JPanel implements ActionListener, Runnable, MouseListener, MouseMotionListener 
{
	private static final long serialVersionUID = 42L;

	// Fields (object variables)
	private GameState game;

	// Methods
	public static void main(String[] args) 
	{
		//Executes our program in the GUI.
		SwingUtilities.invokeLater(new TowerDefense());
	}

	/**
	 * The main run method of the program. It contains the JFrame and all contents.
	 */
	public void run() 
	{
		game = new GameState();
		JFrame f = new JFrame("Halo Radar Defense");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(this);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game Menu");
		JMenuItem menuItem = new JMenuItem("Quit");

		menu.add(menuItem);
		menuBar.add(menu);
		f.setJMenuBar(menuBar);
		menuItem.addActionListener(this);

		f.pack();
		f.setLocationRelativeTo(null); // Centers window
		f.setVisible(true);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
	}

	public TowerDefense() 
	{
		Dimension panelSize = new Dimension(1202, 460); // (backdrop.getWidth(),
		// backdrop.getHeight());
		this.setMinimumSize(panelSize);
		this.setPreferredSize(panelSize);
		this.setMaximumSize(panelSize);

		Timer t = new Timer(16, this);
		t.start();
	}

	/**
	 * Calls the method to draw the image, path, and the animating object.
	 * 
	 * (The background is not cleared, it is assumed the backdrop fills the
	 * panel.)
	 * 
	 * @param g - the graphics object for drawing on this panel
	 */
	public void paint(Graphics g) 
	{
		game.draw(g);
	}

	/**
	 * The actionPerformed method is called (from the GUI event loop) whenever
	 * an action event occurs that this object is listening to.
	 * 
	 * @param e - the event object
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JMenuItem) 
		{
			// Exits the game
			System.exit(0);
		}
		game.update();
		repaint();
	}

	/**
	 * This method modifies the current mouse position if the mouse is moved.
	 */
	public void mouseMoved(MouseEvent e) 
	{
		Point work = new Point((e.getPoint().x - 10), (e.getPoint().y - 35));
		game.setMousePos(work);
	}

	/**
	 * This method sets the isButtonPendingAction variable in GameState to true if the mouse is clicked.
	 */
	public void mouseClicked(MouseEvent e) 
	{
		game.setButtonPendingAction();
	}

	/**
	 * This method sets the isButtonPendingAction variable in GameState to true if the mouse is pressed.
	 */
	public void mousePressed(MouseEvent e) 
	{
		game.setButtonPendingAction();
	}

	/**
	 * This method sets the isButtonPendingAction variable in GameState to false.
	 */
	public void mouseReleased(MouseEvent e) 
	{
		game.clearButtonPendingAction();
	}

	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
