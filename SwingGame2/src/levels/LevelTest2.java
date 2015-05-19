package levels;

import entity.Entity3_Collidable;
import entity.util.Vect2F;

/**
 * @author jose
 *	Class that represents Level 1 in our game.
 */
public class LevelTest2 extends LevelState {

	final String backgroundFile = "/Backgrounds/bg3.gif";

	public LevelTest2(GameStateManager gsm) {
		super(gsm);
		
		// Test entities
		entities.add(new Entity3_Collidable(100,100,100,100));
		entities.add(new Entity3_Collidable(120,120,100,100));
	}

	public void keyPressed(int k) {
		super.keyPressed(k);
		if (k == KEY_LEFT){
			entities.get(0).body.x -= 1;
			entities.get(1).go(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			entities.get(0).body.x += 1;
			entities.get(1).go(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		if (k == KEY_LEFT || k == KEY_RIGHT)
			entities.get(1).stop();
	}
	
}
