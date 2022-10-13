package game;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Tower implements Animatable
{
	protected Point position; 
	protected GameState state; 
	protected BufferedImage towerImg; 
	protected int frameCount; 
	
	public Tower (GameState state, Point position)
	{
		this.position = position; 
		this.state = state; 
	}
	
	public Point setLocation()
	{
		return position; 
	}
}
