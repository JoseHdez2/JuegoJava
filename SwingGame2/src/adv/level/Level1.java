package adv.level;

import bg.Background;
import adv.entity.EntityFinal;

public class Level1 extends LevelState {

	public Level1(GameStateManager gsm) {
		super(gsm);
		
	}
	
	public void init() {
		//entities.add(new EntityFinal(100, 100, 100, 100));
		bg = new Background(backgroundFile);
	}
	
	public void keyPressed(int k) {
		super.keyPressed(k);
	}
	
	public void keyReleased(int k) {
		super.keyReleased(k);
	}
}
