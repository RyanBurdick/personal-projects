package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DeathEffect extends Effect
{
	public int timer;
	public Enemy nearbyEnemy;
	public DeathEffect(GameState state, Point position) 
	{
		super(state, position);
		timer = 0;
		nearbyEnemy = game.findNearestEnemy(position);
	}

	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update() 
	{
		if (timer > 10)
			game.removeAnimatable(this);
		timer ++;
	}

	/**	This method draws the screen and
	 * 	any additional components.
	 * 
	 * @param g A graphics object
	 */
	public void draw(Graphics g) 
	{
		g.drawImage(ResourceLoader.getLoader().getImage("blood.png"), nearbyEnemy.getLocation().x, nearbyEnemy.getLocation().y, 20, 20, null);
	}
}
