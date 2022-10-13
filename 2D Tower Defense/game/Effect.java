package game;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Effect implements Animatable
{
	protected double percentage; 
	protected int frameCounter; 
	protected Path vector; 
	protected Point position; 
	protected GameState game; 
	protected Enemy nearbyEnemy; 
	protected BufferedImage effectImg; 
	
	public Effect (GameState state, Point position)
	{
		this.position = position; 
		this.game = state; 
	}
}
