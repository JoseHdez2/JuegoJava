package levels;

import entity.Entity1_Visible;
import entity.Entity2_Movable;
import entity.Entity3_Collidable;
import entity.util.Vect2F;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class LevelTest3 extends LevelState {

	String backgroundFile = "/Backgrounds/bg3.gif";
	
	public LevelTest3(GameStateManager gsm) {
		super(gsm);
		
		// Test entities
		entities.add(new Entity3_Collidable(20,30,100,100));
		entities.add(new Entity3_Collidable(155,125,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		if (k == KEY_LEFT){
			((Entity2_Movable) entities.get(1)).goX(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			((Entity2_Movable) entities.get(1)).goX(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT)
			((Entity2_Movable) entities.get(1)).stop();
	}
	
}
