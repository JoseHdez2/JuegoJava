package levels;

import entity.Entity3_Collidable;
import entity.util.Vect2F;

/**
 * @author jose
 *	An expansion based on the LevelState class.
 *	Adds player, player input.
 */
public class LevelState2 extends LevelState {
	
	// Player entity.
	int playerStartX = 0;
	int playerStartY = 0;
	int playerW = 100;
	int playerH = 100;
	Entity3_Collidable player = new Entity3_Collidable(100,100,100,100);
	
	public LevelState2(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init(){
		player = new Entity3_Collidable(playerStartX, playerStartY, playerW, playerH);
	}
	
	public void keyPressed(int k) {
		super.keyPressed(k);
		
		// Player input.
		if (k == KEY_LEFT){
			player.go(Vect2F.LEFT, 1f);
		}
		if (k == KEY_RIGHT){
			player.go(Vect2F.RIGHT, 1f);
		}
	}

	public void keyReleased(int k) {
		super.keyReleased(k);
		if (k == KEY_LEFT || k == KEY_RIGHT){
			player.goX(Vect2F.LEFT, 0);
		}
	}
}
