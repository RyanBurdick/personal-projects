package game;

import java.awt.Graphics;
import java.awt.Point;

/**
 * subclass of enemy 
 * Enemy type flood
 * 
 * @author Hong Yang 
 * @version Apr 14, 2017
 */
public class EnemyFlood extends Enemy{

	public EnemyFlood(Path path, GameState state){
		super(path, state); 
		percentage = 0.0; 
		this.path = path; 
		enemyImg = ResourceLoader.getLoader().getImage("infectForm.png"); 	
	}
	//update its movement on the path for the enemy.
	public void update()
	{
		percentage += .0015; 
		if (percentage > 1){
			state.removeAnimatable(this); 
			state.adjustLives(-1);
    		percentage = 0; 
    	}    	

	}
	// draw the image of the enemy
	public void draw (Graphics g){
		 Point p = path.getPathPosition(percentage); 
		 g.drawImage(enemyImg, p.x-5, p.y-5, 10, 10 , null); 
	}
}
