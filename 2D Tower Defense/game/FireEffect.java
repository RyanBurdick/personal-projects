package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class FireEffect extends Effect
{

	public FireEffect(GameState game, Point position) 
	{
		super(game, position);
		frameCounter  = 0; 
		percentage = 0.0; 
		nearbyEnemy = game.findNearestEnemy(position);
		vector = new Path (nearbyEnemy.getLocation(), position); 
		this.vector.getPathLength(); 
	}

	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update() 
	{
		percentage += .0005; 
		frameCounter++; 
		if (nearbyEnemy.getLocation().distance(position) < 20); 
		{
			game.removeAnimatable(nearbyEnemy);
			game.removeAnimatable(this); 
			game.adjustCredits(3);
			game.addAnimatable(new DeathEffect(game, position));
		}
		if (frameCounter == 30)
		{
			game.removeAnimatable(this); 
		}
	}

	/**	This method draws the screen and
	 * 	any additional components.
	 * 
	 * @param g A graphics object
	 */
	public void draw(Graphics g) 
	{
		Point p = vector.getPathPosition(percentage); 
		g.setColor(Color.WHITE);
		g.fillOval(p.x-1, p.y-1, 20, 20);
	}

	public Point getLocation()
	{
		return vector.getPathPosition(percentage); 
	}
}
