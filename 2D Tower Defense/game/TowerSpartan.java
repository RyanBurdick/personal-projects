package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class TowerSpartan extends Tower
{
	//Firing delay variable
	private int timeBetweenShots;
	
	public TowerSpartan(GameState state, Point position)
	{
		super(state, position); 
		timeBetweenShots = 0;
	}
	
	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update() 
	{
		Enemy nearbyEnemy = state.findNearestEnemy(position);
		if (nearbyEnemy == null)
		{
			return; 
		}
		if ((nearbyEnemy.getLocation().distance(position) ) < 100 && timeBetweenShots > 70) 
		{
			state.addAnimatable(new FireEffect(state, this.position));
			timeBetweenShots = 0;
		}
		timeBetweenShots++;
	}

	/**	This method draws the screen and
	 * 	any additional components.
	 * 
	 * @param g A graphics object
	 */
	public void draw(Graphics g) 
	{
		g.setColor(Color.GREEN);
		g.fillOval(position.x-5,position.y-5, 10, 10); 
	}

	/**
	 * This method returns the location of the tower.
	 * @return the location of the tower.
	 */
	public Point getLocation() 
	{
		return position;
	}
}
