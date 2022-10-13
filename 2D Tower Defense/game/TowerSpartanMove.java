package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TowerSpartanMove extends Effect 
{
	public TowerSpartanMove(GameState state, Point position) 
	{
		super(state, position);
	}

	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update() 
	{
		position= game.getMousePos(); 
		if(game.getButtonPendingAction())
		{
			if (position.x <= 1002)
			{
				game.addAnimatable(new TowerSpartan(game , position));
				game.removeAnimatable(this);
				game.adjustCredits(-75);
				game.clearButtonPendingAction();
			}
			if (position.x > 1002)
			{
				game.removeAnimatable(this);
				game.clearButtonPendingAction();
			}
		}
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
}
