package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class EnemyElite extends Enemy
{
		public EnemyElite(Path path, GameState state) 
		{
			super(path, state); 
			percentage = 0.0; 
		}
		
		/**	This method updates the position of the 
		 * 	animation object.
		 */
		public void update()
		{
			percentage += .0012; 
			if (percentage > 1)
			{
				state.removeAnimatable(this); 
				state.adjustLives(-1);
				percentage = 0; 
	    	}    
		}
		
		/**	This method draws the screen and
		 * 	any additional components.
		 * 
		 * @param g A graphics object
		 */
		public void draw (Graphics g)
		{
			 Point p = path.getPathPosition(percentage); 
			 g.setColor(Color.cyan);
			 g.fillOval(p.x-5,p.y-5, 10, 10); 
		}
		
		/**
		 * This method returns the enemy's location on the path.
		 */
		public Point getLocation()
		{
			return path.getPathPosition(percentage); 
		}
	}

