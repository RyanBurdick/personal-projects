package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TowerMarineMenuItem extends Effect {
	

	public TowerMarineMenuItem(GameState state, Point position) {
		super(state, position);
	}

	@Override
	public void update() {
		//System.out.println(state.getMousePos().distance(position) );
		if (game.getButtonPendingAction() && game.getMousePos().distance(position) <= 20 &&  (game.getCredits() >= 25)){
			game.addAnimatable(new TowerMarineMove(game, position));
			//state.clearMousePressed();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(position.x-10,position.y-10, 20, 20); 
		//g.drawImage(effectImg, position.x, position.y, 100,100, null);
	}
}