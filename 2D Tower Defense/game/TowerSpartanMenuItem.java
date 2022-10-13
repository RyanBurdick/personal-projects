package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TowerSpartanMenuItem extends Effect 
{
	public TowerSpartanMenuItem(GameState state, Point position) 
	{
		super(state, position);
	}

	/**	This method updates the position of the 
	 * 	animation object.
	 */
	public void update() 
	{
		if (game.getButtonPendingAction() && game.getMousePos().distance(position) <= 20 &&  (game.getCredits() >= 75))
		{
			game.addAnimatable(new TowerSpartanMove(game, position));
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
		g.fillOval(position.x-10,position.y-10, 20, 20); 
	}
}