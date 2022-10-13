package assignment06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean isPlaying = false;
	
	private int score = 0;
	
	private int totalBlocks = 28;
	
	private Timer tm;
	private int delay = 8;
	
	private int playerX = 300;
	//The starting position of the ball.
	private int ballPosX = 340;
	private int ballPosY = 520;
	//The velocity of the ball in the (x,y) direction. I made it a random number to make it
	//slightly more unpredictable.
	private double ballVelX =  (Math.random() * 3) + 1;
	private double ballVelY = (Math.random() * 3) + 1;
	
	private int centerOfPanelX = 350;
	private int centerOfPanelY = 300;
	
	private Level level;
	
	/**
	 * Game objective:
	 * "Ricochet!" is a simple brickbreaker style game. The player
	 * controls the red paddle at the bottom of the screen, with a two-part objective:
	 * 1) Do not let the ball touch the bottom edge of the screen, or you will lose.
	 * 2) Use the paddle to bounce the ball up towards the blocks, deleting them one at a time.
	 * 
	 * If the player hits all 28 blocks, they win. 
	 * 
	 * Player instructions:
	 * Left arrow key - Move left
	 * Right arrow key - Move right
	 * Spacebar - Restarts the game (if you have lost).
	 * Escape - Quits the game (if you have lost).
	 * 
	 */
	public Gameplay(){
		level = new Level(4, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tm = new Timer(delay, this);
		tm.start();
		
	}
	
	public void paint(Graphics g)
	{
		/**
		 * I do not fully understand the purpose of 'super' or even 'Graphics2D' but
		 * nearly all of the examples I looked at online used it and I could not figure 
		 * out how to get it to work without it.
		 */
		super.paint(g);					
		Graphics2D g2 = (Graphics2D)g; 
		
		//Draw background
		g2.setColor(Color.BLACK);        
		g2.fillRect(0, 0, 700, 600);
		
		//Draw map
		level.draw(g2);
		
		//Draw ball
		g2.setColor(Color.YELLOW);
		g2.fillOval(ballPosX, ballPosY, 20, 20);
		
		//Draw player
		g2.setColor(Color.RED);
		g2.fillRect(playerX, 550, 100, 8);
		
		//Draw welcome screen
		if (!isPlaying && score == 0)
		{
			g2.setColor(getRandomColor());
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			g2.drawString("RICOCHET!", centerOfPanelX - 100, centerOfPanelY);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g2.drawString("Press 'Enter' or the left/right arrow to begin", centerOfPanelX - 170, centerOfPanelY + 30);
		}
		
		//Draw score
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g2.drawString("Score: " + score, 550, 30);
		
		if (totalBlocks == 0)
		{
			//If all blocks have been deleted, then player wins.
			isPlaying = false;
			ballVelX = 0;
			ballVelY = 0;
			g2.setColor(getRandomColor());
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			g2.drawString("You win!", centerOfPanelX - 100, centerOfPanelY);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g2.drawString("Press 'Escape' to exit, or spacebar to continue", centerOfPanelX - 170, centerOfPanelY + 30);
		}
		
		if (ballPosY >= 590)
		{
			//If ball intersects bottom, game over.
			isPlaying = false;
			ballVelX = 0;
			ballVelY = 0;
			g2.setColor(getRandomColor());
			g2.setFont(new Font("Times New Roman", Font.ITALIC, 40));
			g2.drawString("GAME OVER", centerOfPanelX - 120, centerOfPanelY);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g2.drawString("Press 'Escape' to exit, or spacebar to continue", centerOfPanelX - 170, centerOfPanelY + 30);
		}

		g2.dispose();
	}
	/**
	 * This method generates and returns a random color.
	 * 
	 * @return a random color
	 */
	public Color getRandomColor()
	{
		int red = (int)(Math.random()*256);
		int blue = (int)(Math.random()*256);
		int green = (int)(Math.random()*256);
		return new Color(red, green, blue);
				
	}
	public void actionPerformed(ActionEvent e) 
	{
		tm.start();
		if (isPlaying)
		{
			if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
			{
				//Bounce off player
				ballVelY = -ballVelY;
			}

			//Label for outer loop.
			Outer: for (int i = 0; i < level.level.length; i++)
			{
				for (int j = 0; j < level.level[0].length; j++)
				{
					if (level.level[i][j] > 0)
					{
						int blockX = j * level.blockWidth + 80;
						int blockY = i * level.blockHeight + 50;
						int blockWidth = level.blockWidth;
						int blockHeight = level.blockHeight;
						
						Rectangle temp = new Rectangle(blockX, blockY, blockWidth, blockHeight);
						Rectangle ballTemp = new Rectangle(ballPosX, ballPosY, 20, 20);	
						Rectangle blockTemp = temp;
						//If the ball intersects a block, hide the given block from view, increase the score by 10 points
						//and decrement 'totalBlocks'.
						if (ballTemp.intersects(blockTemp))
						{
							level.setBlockValue(0, i, j);
							score += 10;
							totalBlocks--;
							
							if (ballPosX + 19 <= blockTemp.x || ballPosX + 1 >= blockTemp.x + blockTemp.width)
							{
								ballVelX = -ballVelX;
							}
							else
							{
								ballVelY = -ballVelY;
							}
							if (ballVelX < 0)
							{
								ballVelX -= 0.1;
							}
							else if (ballVelX > 0)
							{
								ballVelX += 0.1;
							}
							if (ballVelY < 0)
							{
								ballVelY -= 0.1;
							}
							else if (ballVelY > 0)
							{
								ballVelY += 0.1;
							}	

							//Break Outer loop.
							break Outer;
						}
					}
				}
			}
			ballPosX += ballVelX;
			ballPosY += ballVelY;
			if (ballPosX < 0)
			{
				//Bounce off left wall
				ballVelX = -ballVelX;
			}
			if (ballPosY < 0)
			{
				//Bounce off top wall
				ballVelY = -ballVelY;
			}
			if (ballPosX >= 680)
			{
				//Bounce off right wall
				ballVelX = -ballVelX;
			}
			

			
		}
		repaint();
	}	
	public void keyPressed(KeyEvent e) 
	{
		//Start game
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			isPlaying = true;
		}
		//If player presses left arrow key, move left.
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (playerX <= 0)
				playerX = 0;
			else
				moveLeft();
		}
		//If player presses right arrow key, move right.
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (playerX >= 600)
				playerX = 600;
			else
				moveRight();
		}
		//If the space key is pressed (and 'isPlaying' = false), reset the game.
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(!isPlaying)
			{
				ballPosX = 340;
				ballPosY = 520; 
				ballVelX = Math.random() * 3 + 1;
				ballVelY = Math.random() * 3 + 1;
				playerX = 310;
				score = 0;
				totalBlocks = 28;
				level = new Level(4, 7);
				repaint();
			}
		}
		//If the escape key is pressed (and 'isPlaying' = false), exit the game.
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{	
			if(!isPlaying)
			{
				System.exit(0);
			}
		}
	}	
	public void keyReleased(KeyEvent e) 
	{
		// Leave blank
	}
	public void keyTyped(KeyEvent e) 
	{
		// Leave blank
	}
	public void moveLeft() 
	{
		isPlaying = true;
		playerX -= 10;
	}
	public void moveRight() 
	{
		isPlaying = true;
		playerX += 10;
	}

}
