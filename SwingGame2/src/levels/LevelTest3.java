package levels;

import entity.Entity2_Movable;
import entity.EntityF;
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
		entities.add(new EntityF(20,30,100,100));
		entities.add(new EntityF(155,125,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		if (k == KEY_LEFT){
			entities.get(1).goX(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(1).goX(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT)
			entities.get(1).stop();
	}
	
}
