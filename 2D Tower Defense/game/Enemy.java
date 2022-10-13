package game;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Enemy implements Animatable
{
	protected double percentage; 
	protected Path path; 
	protected GameState state; 
	protected BufferedImage enemyImg; 
	protected int life; 
	
	public Enemy(Path path, GameState state)
	{
		this.path = path; 
		this.state = state; 
	}
	
	public Point getLocation()
	{
		return path.getPathPosition(percentage); 
	}
}
