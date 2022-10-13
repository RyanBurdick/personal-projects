package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
//import sun.audio.*;
import javax.sound.sampled.AudioInputStream;


public class GameState {

	//Current mode (game over, playing, etc.)
	private boolean isPlaying  = false;
	private boolean isGameOver = false; 

	//Score, money, etc.
	private int credits;
	private int life; 

	//List of enemies
	private ArrayList <Animatable> active; 
	private ArrayList <Animatable> expired; 
	private ArrayList <Animatable> pending; 

	//Mouse status/location
	private Point mousePoint; 
	private boolean isButtonPendingAction; 

	//Object variables
	private BufferedImage backdrop;
	private Path path; 

	/**
	 * This is the constructor for the GameState class. It initializes 
	 * many different variables, lists, and objects.
	 */
	public GameState()
	{
		ResourceLoader loader = ResourceLoader.getLoader();
		backdrop = loader.getImage("gameMap1.1.png"); 
		path = loader.getPath("vpathv1.txt"); 
		path.getPathLength(); 
		mousePoint = new Point(0,0); 
		restart();
	}

	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update()
	{		
		if(isPlaying)
		{
			if (Math.random() < .015)
			{
				if (Math.random() <.05)
				{
					active.add(new EnemyElite(path, this)); 
				}
				else{
					active.add(new EnemyGrunt(path, this)); 
				}
			}
			for(Animatable  a : active)
			{
				a.update();
			}

			active.removeAll(expired); 
			expired.clear();
			active.addAll(pending); 
			pending.clear(); 

			isButtonPendingAction = false; 
		}
	}
	
	/**
	 * This method provides a convenient way to "restart" the game
	 * such as when a game over has occurred.
	 */
	public void restart()
	{
		//Make lists
		active = new ArrayList<Animatable>(); 
		expired = new ArrayList<Animatable>(); 
		pending = new ArrayList<Animatable>(); 

		//Initialize the game
		life = 10; 
		credits = 100; 

		//Initialize the lists
		active.add(new TowerMarineMenuItem(this, new Point(1030,75))); 
		active.add(new TowerSpartanMenuItem(this, new Point(1030, 150))); 	
	}

	/**	This method draws the screen and
	 * 	any additional components.
	 * 
	 * @param g A graphics object
	 */
	public void draw (Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(1002, 0, 200, 470);
		g.drawImage(backdrop,  0, 0, null);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(1000, 0, 2, 470);

		//Draw welcome screen
		if(!isPlaying && !isGameOver)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1202, 460);

			g.setColor(Color.WHITE);
			g.setFont(new Font ("Arial", Font.BOLD, 80));
			g.drawString("Halo Radar Defense", 242 , 222);

			g.setColor(Color.BLUE);
			g.setFont(new Font ("Arial", Font.BOLD, 80));
			g.drawString("Halo Radar Defense", 238 , 218);

			g.setColor(Color.CYAN);
			g.setFont(new Font ("Arial", Font.BOLD, 80));
			g.drawString("Halo Radar Defense", 240 , 220);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
			g.drawString("Click to Begin", 450, 300); 
		}

		//Draw these objects when the game is playing.
		if (isPlaying)
		{
			g.setColor(Color.CYAN);
			g.setFont(new Font("calibri", Font.BOLD, 20));
			g.drawString("SHIELD ENERGY " + life, 1010, 20);
			g.drawString("CREDIT: " + getCredits(), 1010, 35);

			g.setColor(Color.WHITE);
			g.setFont(new Font("calibri", Font.BOLD, 18));
			g.drawString("Marine - 25cr", 1050, 80);
			g.drawString("Spartan - 75cr", 1050, 157);

			for(Animatable  a : active)
			{
				a.draw(g);
			}
		}

		//Draw game over screen
		if (isGameOver)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1202, 460);

			isPlaying = false; 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1202, 460);

			g.setColor(Color.magenta);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("GAME OVER!", 298, 218);

			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("GAME OVER!", 304, 222);

			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 100));
			g.drawString("GAME OVER!", 300, 220);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.ITALIC, 50));
			g.drawString("Click to Restart the Game", 350, 280);
		}
	}

	/**
	 * This method adds an Animatable object to the 'expired' list.
	 * @param e An animatable object
	 */
	public void removeAnimatable (Animatable a)
	{
		expired.add(a); 
	}
	
	/**
	 * This method adds an Animatable object to the 'pending' list.
	 * @param e An animatable object
	 */
	public void addAnimatable (Animatable a)
	{
		pending.add(a); 
	}
	
	/**
	 * This method adjusts the credits that the player has.
	 * @param amount An integer to adjust the credits by
	 */
	public void adjustCredits(int amount)
	{
		credits += amount; 
	}
	
	/**
	 * This method returns the amount of credits the player has.
	 * @return the amount of credits the player has.
	 */
	public int getCredits()
	{
		return credits;
	}
	
	/**
	 * This method adjusts the lives that the player has.
	 * @param amount An integer to adjust the lives by
	 */
	public void adjustLives(int amount)
	{
		life += amount; 
		if (life < 0){
			life = 0; 
		}
		if (life == 0){
			isGameOver = true;
		}
	}
	
	/**
	 * This method records the current mouse position.
	 * @param p A point
	 */
	public void setMousePos (Point p)
	{
		mousePoint = p; 
	}
	
	/**
	 * This method returns the current mouse position.
	 * @return The current mouse position
	 */
	public Point getMousePos ()
	{
		return mousePoint; 
	}
	
	/**
	 * This method sets a boolean flag to true (to indicate a mouse press).
	 */
	public void setButtonPendingAction()
	{
		if (!isGameOver && !isPlaying)
		{
			isPlaying = true; 
		}
		if (isGameOver && !isPlaying)
		{
			isGameOver = false;
			isPlaying = true;
			restart(); 
		}
		isButtonPendingAction = true; 
	}
	
	/**
	 * This method clears the mouse press boolean flag.
	 */
	public void clearButtonPendingAction()
	{
		isButtonPendingAction = false; 
	}
	
	/**
	 * This method returns the value of the mouse press flag
	 * @return True if pressed, else false
	 */
	public boolean getButtonPendingAction()
	{
		return isButtonPendingAction; 
	}
	
	/**
	 * This method finds the active enemy nearest to point p, and returns it.
	 * @param p A point
	 * @return The nearest enemy, else null if none
	 */
	public Enemy findNearestEnemy(Point P)
	{
		Enemy near = null ; 
		for(Animatable e : active)
		{
			if (e instanceof Enemy)
			{
				Enemy a  = (Enemy)e; 
				if (near == null)
				{
					near = a; 
				}
				double closeX = a.getLocation().getX() ; 
				double closeY = a.getLocation().getY() ; 
				double  by = Point.distance(closeX, closeY, P.getX(), P.getY()); 
				double  ner = Point.distance(near.getLocation().getX(), near.getLocation().getY(), P.getX(), P.getY()); 
				if(  by<ner ){
					near = a; 
				}
			}
		}
		return near; 
	}
}
