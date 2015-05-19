package levels;

import entity.Entity3_Collidable;
import entity.util.Vect2F;

/**
 * @author jose
 *	Test 1: MRU Movement.
 */
public class LevelTest1 extends LevelState {
	
	public LevelTest1(GameStateManager gsm) {
		super(gsm);
		
		// Test entity
		entities.add(new Entity3_Collidable(100,100,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		// Player input.
		if (k == KEY_LEFT){
			entities.get(0).go(Vect2F.LEFT, 1);
		}
		if (k == KEY_RIGHT){
			entities.get(0).go(Vect2F.RIGHT, 1);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT){
			entities.get(0).stopX();
		}
	}
	
}
