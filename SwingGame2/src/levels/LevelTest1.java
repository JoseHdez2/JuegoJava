package levels;

import entity.Entity1_Visible;
import entity.Entity2_Movable;
import entity.util.Vect2F;

/**
 * @author jose
 *	Test 1: MRU Movement.
 */
public class LevelTest1 extends LevelState {
	
	public LevelTest1(GameStateManager gsm) {
		super(gsm);
		
		// Test entity
		entities.add(new Entity2_Movable(100,100,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		// Player input.
		if (k == KEY_LEFT){
			((Entity2_Movable) entities.get(0)).go(Vect2F.LEFT, 1);
		}
		if (k == KEY_RIGHT){
			((Entity2_Movable) entities.get(0)).go(Vect2F.RIGHT, 1);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT){
			((Entity2_Movable) entities.get(0)).stopX();
		}
	}
	
}
